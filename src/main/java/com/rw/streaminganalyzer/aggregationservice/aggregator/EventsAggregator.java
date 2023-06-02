package com.rw.streaminganalyzer.aggregationservice.aggregator;

import com.rw.streaminganalyzer.aggregationservice.collector.EventsProcessor;
import com.rw.streaminganalyzer.model.input.EventDataWithMetadata;
import com.rw.streaminganalyzer.model.output.EventsAggregatedView;
import com.rw.streaminganalyzer.model.output.UserAggregatedEvents;
import com.rw.streaminganalyzer.modelmapper.ModelMapper;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EventsAggregator {
    private final ModelMapper modelMapper;

    public EventsAggregator(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public Mono<EventsAggregatedView> constructAggregatedView(EventsProcessor eventsProcessor, Flux<EventDataWithMetadata> events) {
        return events.collect(groupEventsByUsers())
                     .map(this::remapToUserAggregatedEvents)
                     .map(map -> buildAggregatedView(eventsProcessor, map));
    }

    private Collector<EventDataWithMetadata, ?, Map<Long, List<EventDataWithMetadata>>> groupEventsByUsers() {
        return Collectors.groupingBy(i -> i.getUserDetails().getId());
    }

    private Map<Long, UserAggregatedEvents> remapToUserAggregatedEvents(Map<Long, List<EventDataWithMetadata>> map) {
        return map.entrySet()
                  .stream()
                  .collect(Collectors.toMap(Map.Entry::getKey,
                                            e -> UserAggregatedEventsCreator.fromEvents(e.getValue(), modelMapper)));
    }

    private EventsAggregatedView buildAggregatedView(EventsProcessor eventsProcessor, Map<Long, UserAggregatedEvents> map) {
        return EventsAggregatedView.builder()
                                   .eventsByUsers(map)
                                   .recentShowsCount(eventsProcessor.recentShowsCount())
                                   .harvestingTimeMs(eventsProcessor.harvestingTimeInMs())
                                   .build();
    }
}
