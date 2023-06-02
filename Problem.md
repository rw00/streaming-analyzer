# Development Challenge #

## the data harvester ##

Write a program that harvests real time usage information
about the users of `video streaming services`.

## requirements ##

Your application should collect the data from all three sources for 20 seconds or until the third occurrence
of a user with first name `Sytac` on either of streams, whichever comes first.

The application should then output the aggregated view of the data collected, detailing:

+ user id
+ user's name and surname
+ user's age
+ all the events that the user has executed
+ platform where each event has occurred
+ the show titles
+ the first person in the cast for each show, if present
+ the show ids
+ event time in  Amsterdam (CET) timezone and `dd-MM-yyyy HH:mm:ss.SSS` format

and also:

+ the total number of shows released in 2020 or later (for any type of event)
+ for how long the 3 http streams were consumed by your harvester program, in milliseconds


<details>
  <summary>Short on time? Here is a hint 💡</summary>

  ```kotlin
    "PT" -> "UTC"
    "CA" -> "America/Toronto"
    "US" -> "America/Los_Angeles"
    "RU" -> "Europe/Moscow"
    "ID" -> "Asia/Jakarta"
    "CN" -> "Asia/Shanghai"
  ```
</details>

<details>
  <summary>Here is another hint, for good luck 💡💡</summary>

  Unfortunately, the server is bugged: from time to time the data returned is not well-formed. Feel free to skip these messages.
</details>
