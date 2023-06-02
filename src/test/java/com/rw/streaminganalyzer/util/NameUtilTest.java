package com.rw.streaminganalyzer.util;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NameUtilTest {


    static Stream<Arguments> testCastStar() {
        return Stream.of(
                Arguments.of("John, Jane, Jesse, Walter", "John"),
                Arguments.of("Jesse,Jane", "Jesse"),
                Arguments.of("Heisenberg", "Heisenberg")
        );
    }

    @ParameterizedTest
    @MethodSource
    void testCastStar(String cast, String expectedStar) {
        Assertions.assertThat(NameUtil.starring(cast)).isEqualTo(expectedStar);
    }
}
