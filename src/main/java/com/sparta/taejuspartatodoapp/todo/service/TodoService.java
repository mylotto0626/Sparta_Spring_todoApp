package com.sparta.taejuspartatodoapp.todo.service;

import com.sparta.taejuspartatodoapp.todo.entity.Todo;
import com.sparta.taejuspartatodoapp.todo.repository.TodoRepository;
import com.sparta.taejuspartatodoapp.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private WeatherService weatherService;

    // 일정 작성 저장
    public Todo createTodo(Todo todo) {
        todo.setCreatedAt(LocalDateTime.now());
        todo.setUpdatedAt(LocalDateTime.now());
        todo.setWeather(weatherService.getTodayWeather());
        return todoRepository.save(todo);
    }

    // 일정 단건 조회
    public Optional<Todo> findTodoById(Long todoId) {
        return todoRepository.findById(todoId);
    }

    // 일정 수정
    public Todo updateTodo(Long todoId, Todo todo) {
        if (todoRepository.existsById(todoId)) {
            todo.setTodoId(todoId);
            todo.setUpdatedAt(LocalDateTime.now());
            return todoRepository.save(todo);
        }
        return null;
    }

    // 일정 삭제
    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }

    // 페이지네이션
    public Page<Todo> findAll(int page, int size) {

        // 수정일 기준 내림차순 정렬
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("updatedAt")));
        return todoRepository.findAll(pageable);
    }
}
