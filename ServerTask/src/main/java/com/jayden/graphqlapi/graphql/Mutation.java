package com.jayden.graphqlapi.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.jayden.graphqlapi.domain.reservation.Reservation;
import com.jayden.graphqlapi.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Mutation implements GraphQLMutationResolver {

    private final ReservationService reservationService;

    public Reservation reservation(Long userId, Long meetingRoomId, String startDt, String endDt) {
        return null;
    }
}
