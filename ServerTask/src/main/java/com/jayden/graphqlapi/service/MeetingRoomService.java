package com.jayden.graphqlapi.service;

import com.jayden.graphqlapi.domain.meetingroom.MeetingRoom;
import com.jayden.graphqlapi.domain.meetingroom.MeetingRoomRepository;
import com.jayden.graphqlapi.dto.MeetingRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MeetingRoomService {

    private final MeetingRoomRepository meetingRoomRepository;

    public MeetingRoom createNew(MeetingRoomDto.CreateRequest request) {
        return meetingRoomRepository.save(request.toEntity());
    }
}
