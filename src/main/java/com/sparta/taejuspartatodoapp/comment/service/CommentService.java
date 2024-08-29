package com.sparta.taejuspartatodoapp.comment.service;

import com.sparta.taejuspartatodoapp.comment.entity.Comment;
import com.sparta.taejuspartatodoapp.comment.repository.CommentRepository;
import com.sparta.taejuspartatodoapp.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TodoRepository todoRepository;

    // 댓글 작성
    public Comment createComment(Comment comment) {
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    // 댓글 단건 조회
    public Optional<Comment> findById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    // 댓글 전체 조회
    public List<Comment> findAllByTodoId(Long todoId) {
        return commentRepository.findByTodoId(todoId);
    }

    // 댓글 수정
    public Comment updateComment(Long commentId, Comment comment) {
        if (commentRepository.existsById(commentId)) {
            comment.setCommentId(commentId);
            comment.setUpdatedAt(LocalDateTime.now());
            return commentRepository.save(comment);
        }
        return null;
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
