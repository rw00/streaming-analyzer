package com.rw.streaminganalyzer.aggregationservice.collector;

import com.rw.streaminganalyzer.MockData;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EventsProcessorTest {

    @Test
    void testProcessing0() {
        EventsProcessor processor = new EventsProcessor();
        Assertions.assertThat(
                List.of(
                        processor.harvest(MockData.eventDataWithMetadata(EventsProcessor.PRIMARY_USER_FIRST_NAME)),
                        processor.harvest(MockData.eventDataWithMetadata(EventsProcessor.PRIMARY_USER_FIRST_NAME)),
                        processor.harvest(MockData.eventDataWithMetadata(EventsProcessor.PRIMARY_USER_FIRST_NAME)),
                        processor.harvest(MockData.eventDataWithMetadata())
                )
        ).isEqualTo(List.of(true, true, true, false));
    }

    @Test
    void testProcessing1() {
        EventsProcessor processor = new EventsProcessor();
        Assertions.assertThat(
                List.of(
                        processor.harvest(MockData.eventDataWithMetadata()),
                        processor.harvest(MockData.eventDataWithMetadata()),
                        processor.harvest(MockData.eventDataWithMetadata(EventsProcessor.PRIMARY_USER_FIRST_NAME)),
                        processor.harvest(MockData.eventDataWithMetadata(EventsProcessor.PRIMARY_USER_FIRST_NAME)),
                        processor.harvest(MockData.eventDataWithMetadata())
                )
        ).isEqualTo(List.of(true, true, true, true, true));
    }
}
