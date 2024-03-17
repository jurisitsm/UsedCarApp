package com.jurisitsm.test.service;

import com.jurisitsm.test.model.AppUser;
import com.jurisitsm.test.repository.UserRepository;
import com.jurisitsm.test.web.dto.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByEmail(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public void createUser(UserRequest userRequest){
        userRepository.save(new AppUser(userRequest.getEmail(), userRequest.getName(), userRequest.getPassword()));
    }

    public void registerLogoutTime(AppUser user){
        user.setLastLogoutTime(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public AppUser loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElse(null);
    }
}
