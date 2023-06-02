package com.rw.streaminganalyzer.model.input;

import java.util.Arrays;

public enum EventType {
    STREAM_STARTED("stream-started"),
    STREAM_FINISHED("stream-finished"), // documented as `stream-ended`
    SHOW_LIKED("show-liked"),
    STREAM_INTERRUPTED("stream-interrupted");

    private final String eventLabel;

    EventType(String eventLabel) {
        this.eventLabel = eventLabel;
    }

    public static EventType fromLabel(String label) {
        return Arrays.stream(EventType.values())
                     .filter(i -> i.eventLabel.equals(label))
                     .findFirst()
                     .orElseThrow();
    }
}
