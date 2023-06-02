package com.rw.streaminganalyzer;

import com.rw.streaminganalyzer.model.input.EventDataWithMetadata;
import com.rw.streaminganalyzer.model.input.EventShowDetails;
import com.rw.streaminganalyzer.model.input.EventUserDetails;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MockData {
    private static final Random RANDOM = new Random();
    private static final AtomicInteger NAME_ID_GENERATOR = new AtomicInteger();

    public EventDataWithMetadata eventDataWithMetadata() {
        return eventDataWithMetadata("John" + NAME_ID_GENERATOR.incrementAndGet());
    }

    public EventDataWithMetadata eventDataWithMetadata(String userFirstName) {
        return EventDataWithMetadata.builder()
                                    .eventId(UUID.randomUUID().toString())
                                    .userDetails(userDetails(userFirstName))
                                    .showDetails(showDetails())
                                    .build();
    }

    public EventUserDetails userDetails(String firstName) {
        return EventUserDetails.builder().firstName(firstName).build();
    }

    public EventShowDetails showDetails() {
        return EventShowDetails.builder()
                               .title("StarWars" + NAME_ID_GENERATOR.incrementAndGet())
                               .releaseYear(randomYear())
                               .build();
    }

    private int randomYear() {
        return RANDOM.nextInt(2050 - 1970) + 1970;
    }
}
