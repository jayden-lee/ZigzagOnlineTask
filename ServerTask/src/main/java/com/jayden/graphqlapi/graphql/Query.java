package com.jayden.graphqlapi.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jayden.graphqlapi.domain.meetingroom.MeetingRoom;
import com.jayden.graphqlapi.domain.reservation.Reservation;
import com.jayden.graphqlapi.service.MeetingRoomService;
import com.jayden.graphqlapi.service.ReservationService;
import com.jayden.graphqlapi.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Query implements GraphQLQueryResolver {

    private final MeetingRoomService meetingRoomService;
    private final ReservationService reservationService;

    public List<Reservation> getThisWeekMeetingRoomReservations() {
        return reservationService.getThisWeekMeetingRoomReservations();
    }

    @Cacheable(value = "meetingRoom")
    public List<MeetingRoom> getEmptyMeetingRooms(String startDt, String endDt) {
        LocalDateTime reservationStartDt = DateTimeUtils.parse(startDt);
        LocalDateTime reservationEndDt = DateTimeUtils.parse(endDt);
        DateTimeUtils.validateStartDateTimeAndEndDateTime(reservationStartDt, reservationEndDt);
        return meetingRoomService.getEmptyMeetingRooms(reservationStartDt, reservationEndDt);
    }
}
