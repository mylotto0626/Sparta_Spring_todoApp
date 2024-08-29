package com.sparta.taejuspartatodoapp.comment.entity;

import com.sparta.taejuspartatodoapp.todo.entity.Todo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todoId")
    private Todo todo;

    private String content;
    private String username;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
