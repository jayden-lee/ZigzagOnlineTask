package com.jayden.graphqlapi.domain.meetingroom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long>, MeetingRoomCustomRepository {
}
