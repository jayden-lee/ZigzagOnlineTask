package com.jayden.graphqlapi.domain.reservation;

import com.jayden.graphqlapi.domain.meetingroom.MeetingRoom;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationCustomRepository {

    List<Reservation> findByMeetingRoomAndStartDtAndEndDt(MeetingRoom meetingRoom, LocalDateTime localStartDt, LocalDateTime localEndDt);
}
