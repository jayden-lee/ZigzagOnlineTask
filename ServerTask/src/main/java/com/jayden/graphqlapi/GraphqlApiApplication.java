package com.jayden.graphqlapi;

import com.jayden.graphqlapi.domain.meetingroom.RoomType;
import com.jayden.graphqlapi.dto.MeetingRoomDto;
import com.jayden.graphqlapi.dto.UserDto;
import com.jayden.graphqlapi.service.MeetingRoomService;
import com.jayden.graphqlapi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

@SpringBootApplication
public class GraphqlApiApplication {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(GraphqlApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDummyData(UserService userService, MeetingRoomService meetingRoomService) {
        return (args) -> {
            userService.createNew(UserDto.CreateRequest.of("jayden", "dev"));
            userService.createNew(UserDto.CreateRequest.of("ella", "planning"));
            userService.createNew(UserDto.CreateRequest.of("robb", "planning"));
            userService.createNew(UserDto.CreateRequest.of("kelly", "dev"));
            userService.createNew(UserDto.CreateRequest.of("cia", "design"));
            userService.createNew(UserDto.CreateRequest.of("bill", "hr"));

            meetingRoomService.createNew(MeetingRoomDto.CreateRequest.of("5-1", RoomType.FOUR));
            meetingRoomService.createNew(MeetingRoomDto.CreateRequest.of("5-2", RoomType.SIX));
            meetingRoomService.createNew(MeetingRoomDto.CreateRequest.of("5-3", RoomType.EIGHT));
            meetingRoomService.createNew(MeetingRoomDto.CreateRequest.of("6-1", RoomType.EIGHT));
            meetingRoomService.createNew(MeetingRoomDto.CreateRequest.of("6-2", RoomType.EIGHT));
        };
    }

}
