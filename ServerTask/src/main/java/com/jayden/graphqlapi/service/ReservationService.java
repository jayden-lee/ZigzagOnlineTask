package com.jayden.graphqlapi.service;

import com.jayden.graphqlapi.domain.meetingroom.MeetingRoom;
import com.jayden.graphqlapi.domain.meetingroom.MeetingRoomRepository;
import com.jayden.graphqlapi.domain.reservation.Reservation;
import com.jayden.graphqlapi.domain.reservation.ReservationRepository;
import com.jayden.graphqlapi.domain.user.User;
import com.jayden.graphqlapi.domain.user.UserRepository;
import com.jayden.graphqlapi.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final UserRepository userRepository;
    private final MeetingRoomRepository meetingRoomRepository;
    private final ReservationRepository reservationRepository;

    // TODO 도메인 클래스로 로직 넣는 리팩토링 필요
    @Transactional
    public Reservation reservation(Long userId, Long meetingRoomId, LocalDateTime localStartDt, LocalDateTime localEndDt) {
        MeetingRoom meetingRoom = meetingRoomRepository.findById(meetingRoomId)
            .orElseThrow(() -> new RuntimeException("This meeting room does not exist."));

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User does not exist"));

        List<Reservation> reservations = reservationRepository.findByMeetingRoomAndStartDtAndEndDt(meetingRoom, localStartDt, localEndDt);
        if (reservations.size() > 0) {
            throw new RuntimeException("The meeting room is already booked at the time.");
        }

        Reservation reservation = Reservation.builder()
            .meetingRoomId(meetingRoom.getId())
            .userId(user.getId())
            .startDt(localStartDt)
            .endDt(localEndDt)
            .build();

        return reservationRepository.save(reservation);
    }

    @Transactional(readOnly = true)
    public List<Reservation> getThisWeekMeetingRoomReservations() {
        LocalDateTime now = LocalDateTime.now();
        final LocalDateTime startDateTime = DateTimeUtils.getThisWeekFirstDateTime(now);
        final LocalDateTime endDateTime = DateTimeUtils.getThisWeekLastDateTime(now);
        return reservationRepository.findByStartDtAndEndDt(startDateTime, endDateTime);
    }
}
