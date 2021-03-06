package com.jayden.graphqlapi.domain.meetingroom;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRoomCustomRepository {

    List<MeetingRoom> findEmptyMeetingRoomList(LocalDateTime startDt, LocalDateTime endDt);
}
