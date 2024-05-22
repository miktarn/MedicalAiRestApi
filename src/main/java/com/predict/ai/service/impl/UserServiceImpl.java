package com.predict.ai.service.impl;

import com.predict.ai.model.User;
import com.predict.ai.model.dto.SaveUserDto;
import com.predict.ai.repository.UserRepository;
import com.predict.ai.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User createUser(SaveUserDto saveUserDto) {
        User user = new User();
        user.setLogin(saveUserDto.login());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean isUserExists(Long userId) {
        return userRepository.existsById(userId);
    }

}
