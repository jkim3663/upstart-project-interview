package com.jkim3663.backend.service.impl;

import com.jkim3663.backend.entity.UserEntity;
import com.jkim3663.backend.repository.UserRepository;
import com.jkim3663.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<String> getAllUserNames() {
        return userRepository.findAll()
                .stream()
                .map(UserEntity::getName)
                .collect(Collectors.toList());
    }
}
