# The video streaming events server #

The `video streaming events server` exposes near real-time information regarding movies streamed by users
on 3 streaming platform: `Sytflix`, `Sytazon` and `Sysney`.  
Each of the endpoints exposes an infinite stream of server sent events representing either the beginning of a streaming (`stream-started`) ,
the end of a streaming (`stream-finished`), liking a show (`show-liked`) or a problem during the streaming of a show
(`stream-interrupted`).

An example message from the stream is shown below:

```
id:664c318c-6f36-424f-adb9-56f7861aba27
event:show-liked
data:{
  "show":   {
    "show_id": "r1",
    "cast": "Bryan Cranston, Aaron Paul",
    "country": "United States",
    "date_added": "January 20, 2008",
    "description": "American crime drama!",
    "director": "Vince Gilligan",
    "duration": "5 Seasons",
    "listed_in": "Crime Drama",
    "rating": "TV-PG",
    "release_year": 2008,
    "title": "Breaking Bad",
    "type": "Series",
    "platform": "Sysney"
  },
  "event_date": "27-02-2023 03:20:17.111",
  "user": {
    "id": 119,
    "date_of_birth": "31/10/1990",
    "email": "rw@myblog.org",
    "first_name": "Lorem",
    "gender": "Male",
    "ip_address": "96.34.189.33",
    "country": "NL",
    "last_name": "Ipsum"
  }
}
```

The `event_date` specifies the user's local time. `Amsterdam (CET)` is the default time zone.
For the users residing in the countries specified in the table below, the `event_date` is provided
in the corresponding time zone.

| User's Residence Country | Country Code | `event_date` Time Zone       |
|--------------------------|--------------|------------------------------|
| Portugal                 | PT           | Europe/Lisbon (UTC)          |
| Canada                   | CA           | America/Toronto (UTC -5)     |
| United States            | US           | America/Los_Angeles (UTC -8) |
| Russia                   | RU           | Europe/Moscow (UTC +3)       |
| Indonesia                | ID           | Asia/Jakarta (UTC +7)        |
| China                    | CN           | Asia/Shanghai (UTC +8)       |

## How to run the video streaming events server locally ##

You will need Docker installed on your machine. Then enter the command:

For Intel/AMDx64-based CPUs:  

```shell
docker run -p 8080:8080 sytacdocker/video-stream-server:latest
```

For ARM-based based CPUs (including Apple with M1/M2 chipsets):  

```shell
docker run -p 8080:8080 sytacdocker/video-stream-server-arm:latest
```

Once the server is running, you will have several endpoints available on your machine:

- [Sytflix](http://localhost:8080/sytflix) (`http://localhost:8080/sytflix`);
- [Sytazon](http://localhost:8080/sytazon) (`http://localhost:8080/sytazon`);
- [Sysney](http://localhost:8080/sysney) (`http://localhost:8080/sysney`).

All the endpoints are protected by username and password (basic auth):

```
username = sytac
password = 4p9g-Dv7T-u8fe-iz6y-SRW2
```
