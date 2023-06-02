package com.rw.streaminganalyzer.controller;

import com.rw.streaminganalyzer.aggregationservice.AggregationService;
import org.springframework.core.io.PathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/aggregation")
public class AggregationController {
    private final AggregationService aggregationService;

    AggregationController(AggregationService aggregationService) {
        this.aggregationService = aggregationService;
    }

    @GetMapping("hello")
    String hello() {
        return "Hello";
    }

    // should be POST, but easier to test like this
    @GetMapping(value = "snapshot", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    Mono<PathResource> takeSnapshot() {
        return aggregationService.aggregateSnapshot().map(PathResource::new);
    }
}
