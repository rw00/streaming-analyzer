package com.rw.streaminganalyzer.model.output;

import com.rw.streaminganalyzer.model.common.StreamingPlatform;
import com.rw.streaminganalyzer.model.input.EventType;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StreamingEvent {
    private EventType eventType;
    private LocalDateTime dateTime;
    private StreamingPlatform platform;
}
