package org.test.monitorsensors.service;

import org.test.monitorsensors.dto.UserRequestDto;
import org.test.monitorsensors.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserResponseDto> findAll();
    UserResponseDto findById(UUID id);
    UserResponseDto create(UserRequestDto userCreateDto);
    UserResponseDto update(UUID id, UserRequestDto userCreateDto);
    void delete(UUID id);
}
