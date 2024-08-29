package com.sparta.taejuspartatodoapp.auth.controller;

import com.sparta.taejuspartatodoapp.auth.entity.User;
import com.sparta.taejuspartatodoapp.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // 유저 생성
    @PostMapping("/register")
    public String create(@RequestBody User user) {
       return userService.createUser(user);
    }

    // 단건 조회
    @GetMapping("/{userId}")
    public ResponseEntity<User> getById(@PathVariable Long userId) {
        Optional<User> user = userService.findUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<Set<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // 유저 수정
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> update(@PathVariable Long userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    // 유저 삭제
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
