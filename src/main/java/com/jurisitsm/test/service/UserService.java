package com.jurisitsm.test.service;

import com.jurisitsm.test.model.AppUser;
import com.jurisitsm.test.repository.UserRepository;
import com.jurisitsm.test.web.dto.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByEmail(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public AppUser getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    public void createUser(UserRequest userRequest){
        userRepository.save(new AppUser(userRequest.getEmail(), userRequest.getName(), userRequest.getPassword()));
    }

    public void registerLogoutTime(AppUser user){
        user.setLastLogoutTime(LocalDateTime.now());
        userRepository.save(user);
    }
}
