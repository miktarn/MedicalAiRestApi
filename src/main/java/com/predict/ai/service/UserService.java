package com.predict.ai.service;

import com.predict.ai.model.User;
import com.predict.ai.model.dto.request.SaveUserDto;
import java.util.List;

public interface UserService {
    User createUser(SaveUserDto saveUserDto);

    List<User> getAllUsers();

    boolean isUserExists(Long userId);
}
