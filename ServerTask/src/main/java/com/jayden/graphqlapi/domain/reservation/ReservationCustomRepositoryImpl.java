package com.jayden.graphqlapi.domain.reservation;

import com.jayden.graphqlapi.domain.meetingroom.MeetingRoom;
import com.jayden.graphqlapi.domain.meetingroom.QMeetingRoom;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationCustomRepositoryImpl extends QuerydslRepositorySupport implements ReservationCustomRepository {

    final QReservation qReservation = QReservation.reservation;
    final QMeetingRoom qMeetingRoom = QMeetingRoom.meetingRoom;

    public ReservationCustomRepositoryImpl() {
        super(Reservation.class);
    }

    @Override
    public List<Reservation> findByMeetingRoomAndStartDtAndEndDt(MeetingRoom meetingRoom, LocalDateTime localStartDt, LocalDateTime localEndDt) {
        BooleanExpression and1 = qReservation.startDt.loe(localStartDt).and(qReservation.endDt.goe(localStartDt));
        BooleanExpression and2 = qReservation.startDt.loe(localEndDt).and(qReservation.endDt.goe(localEndDt));
        return from(qReservation)
            .join(qMeetingRoom)
            .on(qReservation.meetingRoomId.eq(qMeetingRoom.id))
            .where(
                qMeetingRoom.name.eq(meetingRoom.getName())
                    .and(qReservation.startDt.loe(localStartDt).and(qReservation.endDt.goe(localStartDt))
                        .or(qReservation.startDt.loe(localEndDt).and(qReservation.endDt.goe(localEndDt))))
            ).fetch();
    }
}
