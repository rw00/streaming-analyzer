package com.rw.streaminganalyzer.aggregationservice.collector;

import com.rw.streaminganalyzer.model.input.EventDataWithMetadata;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class EventsProcessor {
    public static final Duration MAX_HARVESTING_TIME = Duration.of(20, ChronoUnit.SECONDS);
    static final String PRIMARY_USER_FIRST_NAME = "Sytac";
    private static final int MAX_PRIMARY_USER_EVENT_OCCURRENCES = 3;
    private static final int RECENT_SHOWS_THRESHOLD_YEAR = 2020;
    private final LocalDateTime harvestingStartTime = LocalDateTime.now();
    private final AtomicInteger primaryUserEventOccurrencesCounter = new AtomicInteger();
    private final AtomicInteger recentShowsCounter = new AtomicInteger();
    private final AtomicReference<LocalDateTime> endHarvestingTime = new AtomicReference<>();

    public boolean harvest(EventDataWithMetadata event) {
        if (isTimeLimitReached() || isPrimaryUserEventOccurrencesLimitReached()) {
            return stopHarvesting();
        }
        if ((PRIMARY_USER_FIRST_NAME.equals(event.getUserDetails().getFirstName())
             && (primaryUserEventOccurrencesCounter.incrementAndGet() > MAX_PRIMARY_USER_EVENT_OCCURRENCES))) {
            return stopHarvesting();
        } else {
            return countRecentShow(event);
        }
    }

    public int recentShowsCount() {
        return recentShowsCounter.get();
    }

    public long harvestingTimeInMs() {
        return Duration.between(harvestingStartTime, endHarvestingTime.get()).toMillis();
    }

    private boolean isPrimaryUserEventOccurrencesLimitReached() {
        return primaryUserEventOccurrencesCounter.get() >= MAX_PRIMARY_USER_EVENT_OCCURRENCES;
    }

    private boolean isTimeLimitReached() {
        return Duration.between(harvestingStartTime, LocalDateTime.now()).compareTo(MAX_HARVESTING_TIME) >= 0;
    }

    private boolean countRecentShow(EventDataWithMetadata eventData) {
        if (eventData.getShowDetails().getReleaseYear() >= RECENT_SHOWS_THRESHOLD_YEAR) {
            recentShowsCounter.incrementAndGet();
        }
        return true;
    }

    private boolean stopHarvesting() {
        endHarvestingTime.compareAndSet(null, LocalDateTime.now());
        return false;
    }
}
