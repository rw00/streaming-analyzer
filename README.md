# Streaming-Analyzer

This is a Spring Boot app served from Netty and using reactive WebClient.

The service exposes one endpoint which aggregates events from different data sources. \
The data sources are scraped for the latest events. A snapshot is constructed according to the requirements.

#### Prerequisites

* `JAVA_HOME` environment variable pointing to Java 17 or later

#### Build, Test & Run

Run the following command in the terminal: \
`mvnw spring-boot:run`

#### Check

Go to http://localhost:8081/v1/aggregation/snapshot

#### Architecture

The Service harvests events from the various sources, via the EventsProcessor, until a limit is reached. \
Then, the Service passes the collected events to the EventsAggregator component
which basically builds a HashMap of events per user. \
Finally, the Service delegates the output generation to the EventsAggregatedViewWriter component.

#### Known Issues

1. There isn't "decent" test coverage. The problem is not as simple as you made it seem.
   It took around 9 hours to write decent code.
2. In case there's significant delay between the events, then 
this method could run for longer than 20 seconds.
3. The endpoint doesn't make sense in this scenario because the operation is 20 seconds long.

#### TO DO

1. Add more tests
2. Add validation
3. Fix the `TO DO`s
4. Add logs
