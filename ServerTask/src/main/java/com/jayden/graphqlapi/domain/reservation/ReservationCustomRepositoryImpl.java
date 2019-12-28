package com.jayden.graphqlapi.domain.reservation;

import com.jayden.graphqlapi.domain.reservation.Reservation;
import com.jayden.graphqlapi.domain.reservation.ReservationCustomRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ReservationCustomRepositoryImpl extends QuerydslRepositorySupport implements ReservationCustomRepository {

    public ReservationCustomRepositoryImpl() {
        super(Reservation.class);
    }
}
