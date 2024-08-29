package com.sparta.taejuspartatodoapp.auth.service;


import com.sparta.taejuspartatodoapp.auth.UserDetailsImpl;
import com.sparta.taejuspartatodoapp.auth.config.PasswordEncoder;
import com.sparta.taejuspartatodoapp.auth.entity.User;
import com.sparta.taejuspartatodoapp.auth.jwt.JwtTokenProvider;
import com.sparta.taejuspartatodoapp.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유효한 이메일/비밀번호가 아닙니다."));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("유효한 이메일/비밀번호가 아닙니다.");
        }
        UserDetailsImpl userDetails = UserDetailsImpl.build(user);

        // UserDetailsImpl 객체를 사용하여 JWT 토큰 생성
        return jwtTokenProvider.createToken(userDetails);
    }
}
