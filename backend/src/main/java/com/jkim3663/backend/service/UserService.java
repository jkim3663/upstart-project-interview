package com.jkim3663.backend.service;

import com.jkim3663.backend.dto.UserDTO;
import com.jkim3663.backend.entity.UserEntity;
import com.jkim3663.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<String> getAllUserNames() {
        return userRepository.findAll()
                .stream()
                .map(UserEntity::getName)
                .collect(Collectors.toList());
    }
}
