package com.bs.todo.todo.application.service;

import com.bs.todo.todo.domain.TodoItem;
import com.bs.todo.todo.domain.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TodoItemService {
    private static final Logger log = LoggerFactory.getLogger(TodoItemService.class);
    private final TodoItemRepository repository;

    public TodoItemService(TodoItemRepository repository) {
        this.repository = repository;
    }

    public TodoItem create(String description, LocalDate completionDate) {
        TodoItem item = new TodoItem(description, completionDate);
        TodoItem saved = repository.save(item);
        log.info("Todo item created: {}", saved.getId());
        return saved;
    }

    public List<TodoItem> findAll() {
        return repository.findAll();
    }

    public void delete(UUID id) {
        repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TodoItem not found: " + id));
        repository.deleteById(id);
        log.info("Todo item deleted: {}", id);
    }
}
