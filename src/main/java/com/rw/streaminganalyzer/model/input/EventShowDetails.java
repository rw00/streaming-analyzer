package com.rw.streaminganalyzer.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rw.streaminganalyzer.model.common.StreamingPlatform;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class EventShowDetails {
    @JsonProperty("show_id")
    String showId;
    String title;
    String cast;
    @JsonProperty("release_year")
    int releaseYear;
    StreamingPlatform platform;
}
