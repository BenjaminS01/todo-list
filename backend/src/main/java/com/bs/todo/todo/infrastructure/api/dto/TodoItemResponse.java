package com.bs.todo.todo.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

public class TodoItemResponse  {
    private final UUID id;
    private final String description;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private final LocalDate completionDate;

    public TodoItemResponse(UUID id, String description, LocalDate completionDate) {
        this.id = id;
        this.description = description;
        this.completionDate = completionDate;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }
}
