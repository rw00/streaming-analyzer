package com.rw.streaminganalyzer.aggregationservice.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rw.streaminganalyzer.model.output.EventsAggregatedView;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class EventsAggregatedViewWriter {
    private final ObjectMapper objectMapper;

    public EventsAggregatedViewWriter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Path write(EventsAggregatedView eventsAggregatedView) {
        Path tempFile = Path.of("./tmp/" + System.currentTimeMillis() + "-" + UUID.randomUUID() + ".json");
        try {
            Files.createDirectories(tempFile.getParent());
            objectMapper.writeValue(tempFile.toFile(), eventsAggregatedView);
            return tempFile;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
