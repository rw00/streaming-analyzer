package com.rw.streaminganalyzer.aggregationservice.aggregator;

import com.rw.streaminganalyzer.model.input.EventDataWithMetadata;
import com.rw.streaminganalyzer.model.output.UserAggregatedEvents;
import com.rw.streaminganalyzer.modelmapper.ModelMapper;
import com.rw.streaminganalyzer.util.AgeCalculator;
import com.rw.streaminganalyzer.util.NameUtil;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
class UserAggregatedEventsCreator {

    UserAggregatedEvents fromEvents(List<EventDataWithMetadata> events, ModelMapper modelMapper) {
        EventDataWithMetadata firstEvent = events.get(0);
        long userId = firstEvent.getUserDetails().getId();
        String userFullName = NameUtil.fullName(firstEvent.getUserDetails());
        int userAge = AgeCalculator.calculateAge(firstEvent.getUserDetails().getDateOfBirth());

        return UserAggregatedEvents.builder()
                                   .userId(userId)
                                   .userFullName(userFullName)
                                   .userAge(userAge)
                                   .events(events.stream().map(modelMapper::mapToStreamingEvent).toList())
                                   .shows(events.stream()
                                                .map(i -> modelMapper.mapToStreamedShow(i.getShowDetails()))
                                                .toList())
                                   .build();
    }
}
