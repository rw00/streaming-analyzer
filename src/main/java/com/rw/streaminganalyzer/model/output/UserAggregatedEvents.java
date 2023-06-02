package com.rw.streaminganalyzer.model.output;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAggregatedEvents {
    private long userId;
    private String userFullName;
    private int userAge;
    private List<StreamingEvent> events;
    private List<StreamedShow> shows;
}
