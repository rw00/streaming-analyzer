package com.rw.streaminganalyzer.modelmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rw.streaminganalyzer.model.input.EventData;
import com.rw.streaminganalyzer.model.input.EventDataWithMetadata;
import com.rw.streaminganalyzer.model.input.EventShowDetails;
import com.rw.streaminganalyzer.model.input.EventType;
import com.rw.streaminganalyzer.model.output.StreamedShow;
import com.rw.streaminganalyzer.model.output.StreamingEvent;
import com.rw.streaminganalyzer.util.DateTimeUtil;
import com.rw.streaminganalyzer.util.NameUtil;
import java.time.LocalDateTime;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ModelMapper {
    private final ObjectMapper objectMapper;

    public ModelMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public EventDataWithMetadata mapToEventDataWithMetadata(ServerSentEvent<String> sse) {
        String eventDataAsString = sse.data();
        EventData eventData = parseEventData(eventDataAsString);
        if (eventData == null) {
            return EventDataWithMetadata.EMPTY;
        }
        return EventDataWithMetadata.builder()
                                    .eventId(sse.id())
                                    .eventType(EventType.fromLabel(sse.event()))
                                    .eventDate(eventData.getEventDate())
                                    .userDetails(eventData.getUserDetails())
                                    .showDetails(eventData.getShowDetails())
                                    .build();
    }

    public StreamingEvent mapToStreamingEvent(EventDataWithMetadata eventData) {
        LocalDateTime eventDateTime = DateTimeUtil.convertToDefaultLocalDateTime(eventData.getEventDate(),
                                                                                 eventData.getUserDetails().getCountry());
        return StreamingEvent.builder()
                             .platform(eventData.getShowDetails().getPlatform())
                             .eventType(eventData.getEventType())
                             .dateTime(eventDateTime)
                             .build();
    }

    public StreamedShow mapToStreamedShow(EventShowDetails showDetails) {
        return StreamedShow.builder()
                           .id(showDetails.getShowId())
                           .title(showDetails.getTitle())
                           .starring(NameUtil.starring(showDetails.getCast()))
                           .build();
    }

    private EventData parseEventData(String eventDataAsString) {
        try {
            return objectMapper.readValue(eventDataAsString, EventData.class);
        } catch (JsonProcessingException e) {
            // TO DO : find out why this occurs in the first place
            if (eventDataAsString.length() > 2 && eventDataAsString.charAt(1) == '{') {
                log.warn("Retrying parsing malformed event data");
                return parseEventData(eventDataAsString.substring(1));
            }
            log.warn("Ignoring malformed event " + eventDataAsString, e);
            return null;
        }
    }
}
