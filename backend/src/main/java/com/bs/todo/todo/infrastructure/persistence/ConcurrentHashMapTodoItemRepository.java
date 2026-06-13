package com.bs.todo.todo.infrastructure.persistence;

import com.bs.todo.todo.domain.TodoItem;
import com.bs.todo.todo.domain.TodoItemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ConcurrentHashMapTodoItemRepository implements TodoItemRepository {

    private final Map<UUID, TodoItem> todoList = new ConcurrentHashMap<>();

    @Override
    public TodoItem save(TodoItem todoItem) {
        todoList.put(todoItem.getId(), todoItem);
        return todoItem;
    }

    @Override
    public Optional<TodoItem> findById(UUID id) {
        return Optional.ofNullable(todoList.get(id));
    }

    @Override
    public List<TodoItem> findAll() {
        return todoList.values().stream().toList();
    }

    @Override
    public void deleteById(UUID id) {
        todoList.remove(id);
    }
}
