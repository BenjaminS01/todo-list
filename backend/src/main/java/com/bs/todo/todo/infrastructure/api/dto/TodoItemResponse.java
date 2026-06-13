package com.bs.todo.todo.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

public class TodoItemResponse  {
    private UUID id;
    private String description;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate completionDate;

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
