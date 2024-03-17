package com.jurisitsm.test.service;

import com.jurisitsm.test.model.AppUser;
import com.jurisitsm.test.repository.UserRepository;
import com.jurisitsm.test.web.dto.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public boolean existsByEmail(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public void createUser(UserRequest userRequest){
        userRepository.save(new AppUser(userRequest.getEmail(), userRequest.getName(),
                encoder.encode(userRequest.getPassword())));
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
