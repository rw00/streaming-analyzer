package com.rw.streaminganalyzer.util;

import com.rw.streaminganalyzer.model.common.Country;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DateTimeUtilTest {

    @Test
    void testConvertDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 8, 8, 10, 0);
        Country country = Country.PT;
        Assertions.assertThat(DateTimeUtil.convertToDefaultLocalDateTime(dateTime, country))
                  .isEqualTo(LocalDateTime.of(2022, 8, 8, 11, 0));
    }
}
