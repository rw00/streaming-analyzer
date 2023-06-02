package com.rw.streaminganalyzer.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

// TO DO : configure Jackson to use PascalCase for enums
public enum StreamingPlatform {
    @JsonProperty("Sysney")
    SYSNEY,
    @JsonProperty("Sytazon")
    SYTAZON,
    @JsonProperty("Sytflix")
    SYTFLIX
}
