package com.jayden.graphqlapi.service;

import com.jayden.graphqlapi.domain.meetingroom.MeetingRoom;
import com.jayden.graphqlapi.domain.meetingroom.MeetingRoomRepository;
import com.jayden.graphqlapi.dto.MeetingRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MeetingRoomService {

    private final MeetingRoomRepository meetingRoomRepository;

    @Transactional
    public MeetingRoom createNew(MeetingRoomDto.CreateRequest request) {
        return meetingRoomRepository.save(request.toEntity());
    }

    @Transactional(readOnly = true)
    public List<MeetingRoom> getEmptyMeetingRooms(LocalDateTime startDt, LocalDateTime endDt) {
        return meetingRoomRepository.findByStartDtAndEndDt(startDt, endDt);
    }
}
