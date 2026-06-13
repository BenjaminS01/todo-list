package com.bs.todo.todo.infrastructure.api.controller;


import com.bs.todo.todo.application.service.TodoItemService;
import com.bs.todo.todo.domain.TodoItem;
import com.bs.todo.todo.infrastructure.api.dto.TodoItemRequest;
import com.bs.todo.todo.infrastructure.api.dto.TodoItemResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todos")
public class TodoItemController {

    private final TodoItemService service;

    public TodoItemController(TodoItemService service) {
        this.service = service;
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "TODO entry created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<TodoItemResponse> create(@Valid @RequestBody TodoItemRequest request) {
        TodoItem created = service.create(request.getDescription(), request.getCompletionDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(created));
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "List of all TODO entries")
    public ResponseEntity<List<TodoItemResponse>> findAll() {
        List<TodoItemResponse> response = service.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "TODO entry deleted"),
            @ApiResponse(responseCode = "404", description = "TODO entry not found")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private TodoItemResponse toResponse(TodoItem item) {
        return new TodoItemResponse(item.getId(), item.getDescription(), item.getCompletionDate());
    }

}
