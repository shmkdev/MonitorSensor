package org.test.monitorsensors.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.monitorsensors.dto.UserRequestDto;
import org.test.monitorsensors.dto.UserResponseDto;
import org.test.monitorsensors.entity.User;
import org.test.monitorsensors.mapper.UserMapper;
import org.test.monitorsensors.repository.UserRepository;
import org.test.monitorsensors.service.UserService;
import org.test.monitorsensors.util.exception.DataNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserResponseDto findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public UserResponseDto create(UserRequestDto userRequestDto) {
        return Optional.of(userRequestDto)
                .map(userMapper::toEntity)
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public UserResponseDto update(UUID id, UserRequestDto userRequestDto) {
        return userRepository.findById(id)
                .map(user -> userMapper.toUpdate(user, userRequestDto))
                .map(userRepository::saveAndFlush)
                .map(userMapper::toDto)
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
    }
}
