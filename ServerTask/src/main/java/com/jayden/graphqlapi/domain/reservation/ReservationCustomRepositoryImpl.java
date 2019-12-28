package com.jayden.graphqlapi.domain.reservation;

import com.jayden.graphqlapi.domain.meetingroom.MeetingRoom;
import com.jayden.graphqlapi.domain.meetingroom.QMeetingRoom;
import com.jayden.graphqlapi.domain.user.QUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationCustomRepositoryImpl extends QuerydslRepositorySupport implements ReservationCustomRepository {

    final QUser qUser = QUser.user;
    final QMeetingRoom qMeetingRoom = QMeetingRoom.meetingRoom;
    final QReservation qReservation = QReservation.reservation;

    public ReservationCustomRepositoryImpl() {
        super(Reservation.class);
    }

    @Override
    public List<Reservation> findByMeetingRoomAndStartDtAndEndDt(MeetingRoom meetingRoom, LocalDateTime reservationStartDt, LocalDateTime reservationEndDt) {
        return from(qReservation)
            .join(qMeetingRoom)
            .on(qReservation.meetingRoomId.eq(qMeetingRoom.id))
            .where(
                qMeetingRoom.name.eq(meetingRoom.getName())
                    .and(qReservation.startDt.loe(reservationStartDt).and(qReservation.endDt.goe(reservationStartDt))
                        .or(qReservation.startDt.loe(reservationEndDt).and(qReservation.endDt.goe(reservationEndDt))))
            ).fetch();
    }

    @Override
    public List<Reservation> findByStartDtAndEndDt(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return from(qReservation)
            .join(qMeetingRoom)
            .fetchJoin()
            .on(qReservation.meetingRoomId.eq(qMeetingRoom.id))
            .join(qUser)
            .fetchJoin()
            .on(qReservation.userId.eq(qUser.id))
            .where(
                qReservation.startDt.goe(startDateTime).and(qReservation.endDt.loe(endDateTime))
            ).fetch();
    }
}
