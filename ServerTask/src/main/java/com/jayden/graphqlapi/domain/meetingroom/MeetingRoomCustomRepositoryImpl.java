package com.jayden.graphqlapi.domain.meetingroom;

import com.jayden.graphqlapi.domain.reservation.QReservation;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingRoomCustomRepositoryImpl extends QuerydslRepositorySupport implements MeetingRoomCustomRepository {

    private QMeetingRoom qMeetingRoom = QMeetingRoom.meetingRoom;
    private QReservation qReservation = QReservation.reservation;

    public MeetingRoomCustomRepositoryImpl() {
        super(MeetingRoom.class);
    }

    @Override
    public List<MeetingRoom> findByStartDtAndEndDt(LocalDateTime startDt, LocalDateTime endDt) {
        return from(qMeetingRoom)
            .where(
                qMeetingRoom.id.notIn(
                    JPAExpressions.select(qReservation.meetingRoomId)
                        .from(qReservation)
                        .where(
                            qReservation.startDt.loe(startDt).and(qReservation.endDt.goe(startDt))
                                .or(qReservation.startDt.loe(endDt).and(qReservation.endDt.goe(endDt))))
                )
            )
            .fetch();
    }
}
