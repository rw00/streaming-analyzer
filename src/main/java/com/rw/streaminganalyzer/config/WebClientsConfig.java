package com.rw.streaminganalyzer.config;

import com.rw.streaminganalyzer.config.sources.ServiceConnectionConfigProperties;
import com.rw.streaminganalyzer.config.sources.SysneyConnectionConfigProperties;
import com.rw.streaminganalyzer.config.sources.SytazonConnectionConfigProperties;
import com.rw.streaminganalyzer.config.sources.SytflixConnectionConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientsConfig {
    @Bean
    WebClient sysneyWebClient(SysneyConnectionConfigProperties connectionProperties) {
        return basicAuthWebClient(connectionProperties);
    }

    @Bean
    WebClient sytazonWebClient(SytazonConnectionConfigProperties connectionProperties) {
        return basicAuthWebClient(connectionProperties);
    }

    @Bean
    WebClient sytflixWebClient(SytflixConnectionConfigProperties connectionProperties) {
        return basicAuthWebClient(connectionProperties);
    }

    private WebClient basicAuthWebClient(ServiceConnectionConfigProperties connectionProperties) {
        return WebClient.builder()
                        .baseUrl(connectionProperties.getBaseUrl())
                        .filter(ExchangeFilterFunctions.basicAuthentication(connectionProperties.getUsername(),
                                                                            connectionProperties.getPassword()))
                        .build();
    }
}
