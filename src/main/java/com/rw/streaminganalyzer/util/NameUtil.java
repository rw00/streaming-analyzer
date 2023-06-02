package com.rw.streaminganalyzer.util;

import com.rw.streaminganalyzer.model.input.EventUserDetails;
import java.util.Optional;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NameUtil {
    public String fullName(EventUserDetails userDetails) {
        return userDetails.getFirstName() + " " + userDetails.getLastName();
    }

    public String starring(String castList) {
        return Optional.ofNullable(castList)
                       .map(i -> i.split(",")[0])
                       .map(String::trim)
                       .orElse(null);
    }
}
