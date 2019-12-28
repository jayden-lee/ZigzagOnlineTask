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
```

### Response
```graphql
```

## 주어진 시간에 비어있는 회의실 목록 얻기

### Request
```graphql
```

### Response
```graphql
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