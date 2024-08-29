package com.sparta.taejuspartatodoapp.auth.service;

import com.sparta.taejuspartatodoapp.auth.UserDetailsImpl;
import com.sparta.taejuspartatodoapp.auth.config.PasswordEncoder;
import com.sparta.taejuspartatodoapp.auth.entity.User;
import com.sparta.taejuspartatodoapp.auth.jwt.JwtTokenProvider;
import com.sparta.taejuspartatodoapp.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.HashSet;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        UserDetailsImpl userDetails = UserDetailsImpl.build(savedUser);

        // UserDetailsImpl 객체를 사용하여 JWT 토큰 생성
        return jwtTokenProvider.createToken(userDetails);
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Set<User> findAll() {
        return userRepository.findAll().stream().collect(Collectors.toSet());
    }

    public User updateUser(Long userId, User user) {
        if (userRepository.existsById(userId)) {
            user.setUserId(userId);
            user.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
