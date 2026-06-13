package com.bs.todo.todo.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TodoItemRequest {
    @NotBlank
    @Size(max = 255)
    private String description;

    @NotNull
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate completionDate;

    public String getDescription() {
        return description;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }
}
