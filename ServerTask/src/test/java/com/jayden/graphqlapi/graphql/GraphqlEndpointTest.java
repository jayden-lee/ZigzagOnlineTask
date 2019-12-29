package com.jayden.graphqlapi.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphqlEndpointTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    @DisplayName("회의실 예약이 정상적으로 등록이 되는지 검증하는 테스트")
    public void Reserve_MeetingRoom_Return_ReservationId() throws IOException {
        // given
        ObjectNode variables = new ObjectMapper().createObjectNode();
        variables.put("userId", "5");
        variables.put("meetingRoomId", "5");
        variables.put("start_dt", "2019-12-30T10:00:00");
        variables.put("end_dt", "2019-12-30T11:00:00");

        // when
        GraphQLResponse response = graphQLTestTemplate.perform("reservation.graphql", variables);

        // then
        assertTrue(response.isOk());
        assertNotNull(response.get("$.data.reserveMeetingRoom.id"));
    }

    public GraphQLResponse reserveMeetingRoom(String userId, String meetingRoomId, String startDt, String endDt) throws IOException {
        ObjectNode variables = new ObjectMapper().createObjectNode();
        variables.put("userId", userId);
        variables.put("meetingRoomId", meetingRoomId);
        variables.put("start_dt", startDt);
        variables.put("end_dt", endDt);

        return graphQLTestTemplate.perform("reservation.graphql", variables);
    }

    @Test
    @DisplayName("주어진 시각에 이미 예약이 있어서 에러가 발생하는지 검증하는 테스트")
    public void Reserve_MeetingRoom_Throw_DuplicateReservationException() throws IOException {
        // given
        String userId = "1";
        String meetingRoomId = "1";
        String startDt = "2019-12-28T10:00:00";
        String endDt = "2019-12-28T11:00:00";
        reserveMeetingRoom(userId, meetingRoomId, startDt, endDt);

        // when
        GraphQLResponse response = reserveMeetingRoom(userId, meetingRoomId, startDt, endDt);

        // then
        assertEquals(response.get("$.errors[0].message"), "The meeting room is already booked at the time.");
    }

    @Test
    @DisplayName("이번주 회의실 예약 내역 목록을 검증하는 테스트")
    public void Get_ThisWeek_Reserved_MeetingRoom_List() throws IOException {
        // given
        createThisWeekMeetingRoomDummyData();

        // when
        GraphQLResponse response = graphQLTestTemplate.postForResource("thisWeekMeetingRoom.graphql");

        // then
        assertTrue(response.isOk());
        assertNotNull(response.get("$.data.thisWeekMeetingRoomReservations[0].meetingRoom.name"));
    }

    public void createThisWeekMeetingRoomDummyData() throws IOException {
        String userId = "1";
        String meetingRoomId = "1";
        LocalDateTime startDt = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0);
        LocalDateTime endDt = LocalDateTime.now().plusDays(1).withHour(12).withMinute(0).withSecond(0);
        reserveMeetingRoom(userId, meetingRoomId, startDt.toString(), endDt.toString());

        userId = "2";
        meetingRoomId = "2";
        startDt = LocalDateTime.now().withHour(15).withMinute(0).withSecond(0);
        endDt = LocalDateTime.now().withHour(16).withMinute(0).withSecond(0);
        reserveMeetingRoom(userId, meetingRoomId, startDt.toString(), endDt.toString());

        userId = "3";
        meetingRoomId = "3";
        startDt = LocalDateTime.now().minusDays(1).withHour(13).withMinute(0).withSecond(0);
        endDt = LocalDateTime.now().minusDays(1).withHour(15).withMinute(0).withSecond(0);
        reserveMeetingRoom(userId, meetingRoomId, startDt.toString(), endDt.toString());
    }

    @Test
    @DisplayName("주어진 시간에 비어 있는 회의실 목록을 검증하는 테스트")
    public void Empty_MeetingRoom_List() throws IOException {
        // given
        LocalDateTime startDt = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0);
        LocalDateTime endDt = LocalDateTime.now().plusDays(1).withHour(12).withMinute(0).withSecond(0);

        ObjectNode variables = new ObjectMapper().createObjectNode();
        variables.put("start_dt", startDt.toString());
        variables.put("end_dt", endDt.toString());

        // when
        GraphQLResponse response = graphQLTestTemplate.postForResource("emptyMeetingRoom.graphql");

        // then
        assertTrue(response.isOk());
    }
}
