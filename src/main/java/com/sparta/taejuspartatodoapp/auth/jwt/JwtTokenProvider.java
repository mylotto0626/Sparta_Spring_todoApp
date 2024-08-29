package com.sparta.taejuspartatodoapp.auth.jwt;

import com.sparta.taejuspartatodoapp.auth.UserDetailsImpl;
import com.sparta.taejuspartatodoapp.auth.entity.RoleEnum;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import java.util.Date;


@Component
public class JwtTokenProvider {
    private final String SECRET_KEY = "your_secret_key";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; //10 hours

    public String createToken(UserDetailsImpl userDetails) {
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("userId", userDetails.getUserId());
        claims.put("email", userDetails.getEmail());
        claims.put("role", userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", ""));

        Date now = new Date();
        Date validity = new Date(now.getTime() + 36000000); // 10시간 유효

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // JWT 토큰에서 UserDetailsImpl 얻기
    public UserDetailsImpl getUserDetails(String token) {
        Claims claims = getClaims(token);
        Long userId = Long.parseLong(claims.get("userId", String.class));
        String username = claims.getSubject();
        String email = claims.get("email", String.class);
        String role = claims.get("role", String.class);

        // RoleEnum으로 변환
        RoleEnum roleEnum = RoleEnum.valueOf(role);

        return new UserDetailsImpl(userId, username, email, roleEnum);
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return (token != null && token.startsWith("Bearer ")) ? token.substring(7) : null;
    }

}
