package com.bs.todo.todo.application.service;

import com.bs.todo.todo.domain.TodoItem;
import com.bs.todo.todo.domain.TodoItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TodoItemService {
    private final TodoItemRepository repository;

    public TodoItemService(TodoItemRepository repository) {
        this.repository = repository;
    }

    public TodoItem create(String description, LocalDate completionDate) {
        TodoItem item = new TodoItem(description, completionDate);
        return repository.save(item);
    }

    public List<TodoItem> findAll() {
        return repository.findAll();
    }

    public void delete(UUID id) {
        repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TodoItem not found: " + id));
        repository.deleteById(id);
    }
}
