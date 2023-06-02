package com.rw.streaminganalyzer.aggregationservice;

import com.rw.streaminganalyzer.aggregationservice.aggregator.EventsAggregator;
import com.rw.streaminganalyzer.aggregationservice.collector.EventsProcessor;
import com.rw.streaminganalyzer.aggregationservice.output.EventsAggregatedViewWriter;
import com.rw.streaminganalyzer.model.input.EventDataWithMetadata;
import com.rw.streaminganalyzer.model.output.EventsAggregatedView;
import com.rw.streaminganalyzer.modelmapper.ModelMapper;
import java.nio.file.Path;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AggregationService {
    private static final ParameterizedTypeReference<ServerSentEvent<String>> EVENT_TYPE
            = new ParameterizedTypeReference<>() {
    };
    private final List<WebClient> webClients;
    private final ModelMapper modelMapper;
    private final EventsAggregator eventsAggregator;
    private final EventsAggregatedViewWriter eventsAggregatedViewWriter;

    public AggregationService(List<WebClient> webClients, ModelMapper modelMapper, EventsAggregator eventsAggregator, EventsAggregatedViewWriter eventsAggregatedViewWriter) {
        this.webClients = webClients;
        this.modelMapper = modelMapper;
        this.eventsAggregator = eventsAggregator;
        this.eventsAggregatedViewWriter = eventsAggregatedViewWriter;
    }


    public Mono<Path> aggregateSnapshot() {
        EventsProcessor eventsProcessor = new EventsProcessor();
        Flux<EventDataWithMetadata> eventsFromAllSources = Flux.merge(webClients.stream()
                                                                                .map(source -> collect(source,
                                                                                                       eventsProcessor))
                                                                                .toList());
        Mono<EventsAggregatedView> eventsAggregatedView = eventsAggregator.constructAggregatedView(eventsProcessor,
                                                                                                   eventsFromAllSources);
        return eventsAggregatedView.map(eventsAggregatedViewWriter::write);
    }

    private Flux<EventDataWithMetadata> collect(WebClient webClient, EventsProcessor eventsProcessor) {
        return webClient.get().retrieve().bodyToFlux(EVENT_TYPE)
                        .map(modelMapper::mapToEventDataWithMetadata)
                        .filter(event -> (event != EventDataWithMetadata.EMPTY))
                        // .take(EventsProcessor.MAX_HARVESTING_TIME)
                        // but need a callback to eventsProcessor.stopHarvesting()
                        .takeWhile(eventsProcessor::harvest);
    }
}
