package com.jayden.graphqlapi.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jayden.graphqlapi.domain.meetingroom.MeetingRoom;
import com.jayden.graphqlapi.domain.reservation.Reservation;
import com.jayden.graphqlapi.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Query implements GraphQLQueryResolver {

    private final ReservationService reservationService;

    public List<Reservation> getThisWeekMeetingRoomReservations() {
        return reservationService.getThisWeekMeetingRoomReservations();
    }

    public List<MeetingRoom> getEmptyMeetingRooms(String startDt, String endDt) {
        return Collections.EMPTY_LIST;
    }
}
