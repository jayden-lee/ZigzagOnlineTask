# GraphQL Server Task
> GraphQL을 이용한 간단한 회의실 예약 시스템 API 서버

## 회의실 예약 시스템 API

## 회의실 예약

### Request
```graphql
mutation {
  reservation(userId: 1, meetingRoomId: 1, start_dt:"2019-12-28T10:00:00", end_dt:"2019-12-28T11:00:00") 
  {
    id
  }
}
```

### Response
```json
{
  "data": {
    "reservation": {
      "id": "1"
    }
  }
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
```

### Response
```json
```

## GraphQL Testing
- http://localhost:8080/graphiql

## Run Application
```
mvn spring-boot:run
```

## References
- https://www.baeldung.com/spring-graphql
- https://itnext.io/beginners-guide-to-graphql-with-spring-boot-69d229e87b19