package com.sparta.taejuspartatodoapp.comment.controller;

import com.sparta.taejuspartatodoapp.comment.entity.Comment;
import com.sparta.taejuspartatodoapp.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.createComment(comment));
    }

    // 댓글 단건 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> findById(@PathVariable Long commentId) {
        Optional<Comment> comment = commentService.findById(commentId);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 댓글 전체 조회 (todoId)
    @GetMapping("/todo/{todoId}")
    public ResponseEntity<List<Comment>> findAllByTodoId(@PathVariable Long todoId) {
        return ResponseEntity.ok(commentService.findAllByTodoId(todoId));
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestBody Comment comment) {
        Comment updatedComment = commentService.updateComment(commentId, comment);
        return updatedComment != null ? ResponseEntity.ok(updatedComment) : ResponseEntity.notFound().build();
    }

    // 댓글 삭제
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

}
