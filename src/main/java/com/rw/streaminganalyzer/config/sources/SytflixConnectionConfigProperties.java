package com.rw.streaminganalyzer.config.sources;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("streaming-data-sources.sytflix")
@Value
public class SytflixConnectionConfigProperties implements ServiceConnectionConfigProperties {
    String baseUrl;
    String username;
    String password;
}
