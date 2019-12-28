package com.jayden.graphqlapi.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.jayden.graphqlapi.domain.reservation.Reservation;
import com.jayden.graphqlapi.service.ReservationService;
import com.jayden.graphqlapi.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class Mutation implements GraphQLMutationResolver {

    private final ReservationService reservationService;

    @CacheEvict(value="meetingRoom", allEntries = true)
    public Reservation reservation(Long userId, Long meetingRoomId, String startDt, String endDt) {
        LocalDateTime reservationStartDt = LocalDateTime.parse(startDt);
        LocalDateTime reservationEndDt = LocalDateTime.parse(endDt);
        DateTimeUtils.validateDate(reservationStartDt, reservationEndDt);
        return reservationService.reservation(userId, meetingRoomId, reservationStartDt, reservationEndDt);
    }
}