package com.jayden.graphqlapi.service;

import com.jayden.graphqlapi.domain.user.User;
import com.jayden.graphqlapi.domain.user.UserRepository;
import com.jayden.graphqlapi.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createNew(UserDto.CreateRequest request) {
        return userRepository.save(request.toEntity());
    }
}
