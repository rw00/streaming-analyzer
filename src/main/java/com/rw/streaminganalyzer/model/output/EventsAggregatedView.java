package com.rw.streaminganalyzer.model.output;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventsAggregatedView {
    private Map<Long, UserAggregatedEvents> eventsByUsers;
    private int recentShowsCount;
    private long harvestingTimeMs;
}
