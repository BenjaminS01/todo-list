package com.bs.todo.todo.application.service;

import com.bs.todo.todo.domain.TodoItem;
import com.bs.todo.todo.domain.TodoItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoItemServiceTest {

    @Mock
    private TodoItemRepository todoItemRepository;

    @InjectMocks
    private TodoItemService todoItemService;

    private TodoItem todoItem;

    @BeforeEach
    void setUp() {
        todoItem = new TodoItem("Rasen mähen", LocalDate.now());
    }

    @Test
    void shouldSaveAndReturn() {
        //given
        when(todoItemRepository.save(any(TodoItem.class))).thenReturn(todoItem);
        //when
        TodoItem result = todoItemService.create("Rasen mähen", LocalDate.now());
        //then
        assertNotNull(result);
        verify(todoItemRepository, times(1)).save(any(TodoItem.class));
    }

    @Test
    void shouldFindAll() {
        //given
        when(todoItemRepository.findAll()).thenReturn(List.of(todoItem));
        //when
        List<TodoItem> result = todoItemService.findAll();
        //then
        assertEquals(1, result.size());
        verify(todoItemRepository, times(1)).findAll();
    }

    @Test
    void shouldDelete() {
        // Given
        when(todoItemRepository.findById(todoItem.getId())).thenReturn(Optional.of(todoItem));
        // When
        todoItemService.delete(todoItem.getId());
        // Then
        verify(todoItemRepository, times(1)).deleteById(todoItem.getId());
    }

    @Test
    void shouldThrowWhenDeletingUnknownId() {
        // Given
        UUID unknownId = UUID.randomUUID();
        when(todoItemRepository.findById(unknownId)).thenReturn(Optional.empty());
        // When / Then
        assertThrows(NoSuchElementException.class, () -> todoItemService.delete(unknownId));
        verify(todoItemRepository, never()).deleteById(any());
    }
}