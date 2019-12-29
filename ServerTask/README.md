# GraphQL Server Task
> GraphQL을 이용한 간단한 회의실 예약 시스템 API 서버

## Using Dependency Module
- Spring Boot, JPA, Cache, Web
- GraphQL
- MySQL
- QueryDSL
- ...

## 회의실 예약 시스템 API
- 시간 타임존은 UTC를 기준으로 설정
- 시간 포맷팅은 ISO_DATE_TIME(ex:2020-01-01T00:00:00)으로 설정
- 사용자와 회의실 정보는 더미 데이터로 미리 생성
- 회의실 예약 정보가 새로 생성되면, 캐싱된 빈 회의실 목록 정보를 제거
- 동일한 날짜 값으로 빈 회의실 목록을 다시 조회하면 캐싱된 정보를 전달  

## 회의실 예약

### Request
```graphql
mutation {
  reserveMeetingRoom(userId: 1, meetingRoomId: 1, start_dt:"2019-12-28T10:00:00", end_dt:"2019-12-28T11:00:00") 
  {
    id
  }
}
```

### Success Response
```json
{
  "data": {
    "reservation": {
      "id": "1"
    }
  }
}
```

### Error Response
```json
{
    "data": {
        "reserveMeetingRoom": null
    },
    "errors": [
        {
            "message": "The meeting room is already booked at the time.",
            "path": [
                "reserveMeetingRoom"
            ],
            "extensions": {},
            "locations": [
                {
                    "line": 2,
                    "column": 3,
                    "sourceName": null
                }
            ],
            "errorType": "DataFetchingException"
        }
    ]
}
```

## 이번 주 회의실 예약 내역 얻기

### Request
```graphql
query {
  thisWeekMeetingRoomReservations
  {
    user {
      department
    },
    meetingRoom {
      name
    },
    startDt,
    endDt
  }
}
```

### Response
```json
{
  "data": {
    "thisWeekMeetingRoomReservations": [
      {
        "user": {
          "department": "dev"
        },
        "meetingRoom": {
          "name": "5-1"
        },
        "startDt": "2019-12-28T12:00",
        "endDt": "2019-12-28T13:00"
      },
      {
        "user": {
          "department": "planning"
        },
        "meetingRoom": {
          "name": "5-2"
        },
        "startDt": "2019-12-23T12:00",
        "endDt": "2019-12-23T13:00"
      }
    ]
  }
}
```

## 주어진 시간에 비어있는 회의실 목록 얻기

### Request
```graphql
query {
  emptyMeetingRooms(startDt: "2019-12-28T12:00:00", endDt: "2019-12-28T12:59:00")
  {
    name,
    type
  }
}
```

### Response
```json
{
  "data": {
    "emptyMeetingRooms": [
      {
        "name": "5-1",
        "type": "FOUR"
      },
      {
        "name": "5-2",
        "type": "SIX"
      },
      {
        "name": "5-3",
        "type": "EIGHT"
      },
      {
        "name": "6-1",
        "type": "EIGHT"
      },
      {
        "name": "6-2",
        "type": "EIGHT"
      }
    ]
  }
}
```

## GraphQL Schema
- http://localhost:8080/graphql/schema.json

## GraphQL Testing
- http://localhost:8080/graphiql

## Run Application
```
mvn spring-boot:run
```

## Dummy Data
```sql
INSERT INTO `meetingroom` (`name`, `type`) VALUES ('5-1', 'FOUR');
INSERT INTO `meetingroom` (`name`, `type`) VALUES ('5-2', 'SIX');
INSERT INTO `meetingroom` (`name`, `type`) VALUES ('5-3', 'EIGHT');
INSERT INTO `meetingroom` (`name`, `type`) VALUES ('6-1', 'EIGHT');
INSERT INTO `meetingroom` (`name`, `type`) VALUES ('6-2', 'EIGHT');

INSERT INTO `user` (`department`, `name`) VALUES ('dev', 'jayden');
INSERT INTO `user` (`department`, `name`) VALUES ('planning', 'ella');
INSERT INTO `user` (`department`, `name`) VALUES ('planning', 'robb');
INSERT INTO `user` (`department`, `name`) VALUES ('dev', 'kelly');
INSERT INTO `user` (`department`, `name`) VALUES ('design', 'cia');
INSERT INTO `user` (`department`, `name`) VALUES ('hr', 'bill');
```

## References
- https://www.baeldung.com/spring-graphql
- https://itnext.io/beginners-guide-to-graphql-with-spring-boot-69d229e87b19