package com.bs.todo.todo.infrastructure.persistence;

import com.bs.todo.todo.domain.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ConcurrentHashMapTodoItemRepositoryTest {
    private ConcurrentHashMapTodoItemRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ConcurrentHashMapTodoItemRepository();
    }

    @Test
    void shouldSaveAndReturnTodoItem() {
        // Given
        TodoItem item = new TodoItem("Einkaufen", LocalDate.now());
        // When
        TodoItem saved = repository.save(item);
        // Then
        assertEquals(item, saved);
    }

    @Test
    void shouldFindById() {
        // Given
        TodoItem item = new TodoItem("Essen gehen", LocalDate.now());
        repository.save(item);
        // When
        Optional<TodoItem> result = repository.findById(item.getId());
        // Then
        assertTrue(result.isPresent());
        assertEquals(item, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalForUnknownId() {
        // Given
        UUID unknownId = UUID.randomUUID();
        // When
        Optional<TodoItem> result = repository.findById(unknownId);
        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindAll() {
        // Given
        TodoItem item1 = new TodoItem("Schwimmen", LocalDate.now());
        TodoItem item2 = new TodoItem("Rasen Mähen", LocalDate.now());
        repository.save(item1);
        repository.save(item2);
        // When
        List<TodoItem> result = repository.findAll();
        // Then
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnEmptyListWhenNoEntries() {
        // When
        List<TodoItem> result = repository.findAll();
        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldDeleteById() {
        // Given
        TodoItem item = new TodoItem("Nichts tun", LocalDate.now());
        repository.save(item);
        // When
        repository.deleteById(item.getId());
        // Then
        assertTrue(repository.findById(item.getId()).isEmpty());
    }

    @Test
    void shouldNotFailWhenDeletingUnknownId() {
        UUID unknownId = UUID.randomUUID();
        assertDoesNotThrow(() -> repository.deleteById(unknownId));
    }
}
