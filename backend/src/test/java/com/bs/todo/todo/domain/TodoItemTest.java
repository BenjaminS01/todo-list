package com.bs.todo.todo.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

class TodoItemTest {

    @Test
    public void shouldGetAttributes() {
        // Given
        String description = "valid description";
        LocalDate localDate = LocalDate.now();
        // When
        TodoItem todoItem = new TodoItem(description, localDate);
        // Then
        Assertions.assertNotNull(todoItem.getId());
        Assertions.assertEquals("valid description", todoItem.getDescription());
        Assertions.assertEquals(localDate, todoItem.getCompletionDate());
    }

    @Test
    public void shouldGenerateUniqueId() {
        // Given
        String description1 = "Sport";
        LocalDate localDate1 = LocalDate.now();
        String description2 = "Steuererklärung";
        LocalDate localDate2 = LocalDate.now();
        // When
        TodoItem todoItem1 = new TodoItem(description1, localDate1);
        TodoItem todoItem2 = new TodoItem(description2, localDate2);
        TodoItem todoItem3 = new TodoItem(description1, localDate1);
        Set<UUID> ids = Set.of(todoItem1.getId(), todoItem2.getId(), todoItem3.getId());
        // Then
        Assertions.assertEquals(3, ids.size());
    }

//    @Test
//    public void shouldThrowForInvalidDateFormat() {
//
//    }
//
//    @Test
//    public void shouldThrowForBlankDescription() {
//
//    }
//
//    @Test
//    public void shouldThrowForOversizeDescription() {
//
//    }
}
