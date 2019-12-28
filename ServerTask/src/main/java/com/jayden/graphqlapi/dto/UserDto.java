package com.jayden.graphqlapi.dto;

import com.jayden.graphqlapi.domain.user.User;
import lombok.Getter;
import lombok.Setter;

public class UserDto {

    @Getter
    @Setter
    public static class CreateRequest {
        private String name;
        private String department;

        public static CreateRequest of(String name, String department) {
            CreateRequest request = new CreateRequest();
            request.name = name;
            request.department = department;
            return request;
        }

        public User toEntity() {
            return User.builder()
                .name(name)
                .department(department)
                .build();
        }
    }
}
