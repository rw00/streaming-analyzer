package com.rw.streaminganalyzer.model.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
public class EventDataWithMetadata {
    public static final EventDataWithMetadata EMPTY = EventDataWithMetadata.builder().build();

    private String eventId;
    private EventType eventType;
    @JsonProperty("event_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss.SSS")
    private LocalDateTime eventDate;
    @JsonProperty("show")
    private EventShowDetails showDetails;
    @JsonProperty("user")
    private EventUserDetails userDetails;
}
