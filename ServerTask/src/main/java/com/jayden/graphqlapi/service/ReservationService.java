package com.jayden.graphqlapi.service;

import com.jayden.graphqlapi.domain.meetingroom.MeetingRoom;
import com.jayden.graphqlapi.domain.meetingroom.MeetingRoomRepository;
import com.jayden.graphqlapi.domain.reservation.Reservation;
import com.jayden.graphqlapi.domain.reservation.ReservationRepository;
import com.jayden.graphqlapi.domain.user.User;
import com.jayden.graphqlapi.domain.user.UserRepository;
import com.jayden.graphqlapi.exception.DuplicateReservationException;
import com.jayden.graphqlapi.exception.InvalidArgumentException;
import com.jayden.graphqlapi.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final UserRepository userRepository;
    private final MeetingRoomRepository meetingRoomRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public Reservation reserveMeetingRoom(Long userId, Long meetingRoomId, LocalDateTime startDt, LocalDateTime endDt) {
        MeetingRoom meetingRoom = meetingRoomRepository.findById(meetingRoomId)
            .orElseThrow(() -> new InvalidArgumentException("This meeting room does not exist.", meetingRoomId));

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new InvalidArgumentException("User does not exist.", userId));

        List<Reservation> reservations = reservationRepository.findAlreadyReservedMeetingRoomList(meetingRoom, startDt, endDt);
        if (!CollectionUtils.isEmpty(reservations)) {
            throw new DuplicateReservationException();
        }

        Reservation reservation = Reservation.builder()
            .meetingRoomId(meetingRoom.getId())
            .userId(user.getId())
            .startDt(startDt)
            .endDt(endDt)
            .build();

        return reservationRepository.save(reservation);
    }

    @Transactional(readOnly = true)
    public List<Reservation> getThisWeekMeetingRoomReservations() {
        LocalDateTime now = LocalDateTime.now();
        final LocalDateTime startDateTime = DateTimeUtils.getThisWeekFirstDateTime(now);
        final LocalDateTime endDateTime = DateTimeUtils.getThisWeekLastDateTime(now);
        return reservationRepository.findThisWeekReservedMeetingRoomList(startDateTime, endDateTime);
    }
}
