package com.rw.streaminganalyzer.model.output;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StreamedShow {
    private String id;
    private String title;
    private String starring;
}
