package com.jayden.graphqlapi.dto;

import com.jayden.graphqlapi.domain.meetingroom.MeetingRoom;
import com.jayden.graphqlapi.domain.meetingroom.RoomType;
import lombok.Getter;
import lombok.Setter;

public class MeetingRoomDto {

    @Getter
    @Setter
    public static class CreateRequest {
        private String name;
        private RoomType type;

        public static CreateRequest of(String name, RoomType type) {
            CreateRequest request = new CreateRequest();
            request.name = name;
            request.type = type;
            return request;
        }

        public MeetingRoom toEntity() {
            return MeetingRoom.builder()
                .name(name)
                .type(type)
                .build();
        }
    }
}
