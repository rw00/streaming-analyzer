package com.rw.streaminganalyzer.config.sources;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("streaming-data-sources.sytazon")
@Value
public class SytazonConnectionConfigProperties implements ServiceConnectionConfigProperties {
    String baseUrl;
    String username;
    String password;
}
