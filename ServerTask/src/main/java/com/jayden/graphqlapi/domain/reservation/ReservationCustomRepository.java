package com.jayden.graphqlapi.domain.reservation;

import com.jayden.graphqlapi.domain.meetingroom.MeetingRoom;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationCustomRepository {

    List<Reservation> findAlreadyReservedMeetingRoomList(MeetingRoom meetingRoom, LocalDateTime reservationStartDt, LocalDateTime reservationEndDt);

    List<Reservation> findThisWeekReservedMeetingRoomList(LocalDateTime startDt, LocalDateTime endDt);
}
