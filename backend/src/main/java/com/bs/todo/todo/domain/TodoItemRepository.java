package com.bs.todo.todo.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoItemRepository {

    TodoItem save(TodoItem todoItem);

    Optional<TodoItem> findById(UUID id);

    List<TodoItem> findAll();

    void deleteById(UUID id);

}
