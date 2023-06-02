package com.rw.streaminganalyzer.config.sources;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("streaming-data-sources.sysney")
@Value
public class SysneyConnectionConfigProperties implements ServiceConnectionConfigProperties {
    String baseUrl;
    String username;
    String password; // TO DO : store in DB as encrypted and read here
}
