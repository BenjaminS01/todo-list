package com.bs.todo.todo.domain;


import java.time.LocalDate;
import java.util.UUID;

public class TodoItem {

    private final UUID id = UUID.randomUUID();
    private final String description;
    private final LocalDate completionDate;

    public TodoItem(String description, LocalDate completionDate) {
        this.description = description;
        this.completionDate = completionDate;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public UUID getId() {
        return this.id;
    }
}
