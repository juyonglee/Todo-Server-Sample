package com.gmail.juyonglee0208.controller;

import com.gmail.juyonglee0208.model.TodoEntity;
import com.gmail.juyonglee0208.model.TodoRequest;
import com.gmail.juyonglee0208.model.TodoResponse;
import com.gmail.juyonglee0208.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest todoRequest) {
        if(ObjectUtils.isEmpty(todoRequest.getTitle()))
            return ResponseEntity.badRequest().build();

        if(ObjectUtils.isEmpty(todoRequest.getOrder()))
            todoRequest.setOrder(0L);

        if(ObjectUtils.isEmpty(todoRequest.getCompleted()))
            todoRequest.setCompleted(false);

        return ResponseEntity.ok(new TodoResponse(this.todoService.add(todoRequest)));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        return ResponseEntity.ok(new TodoResponse(this.todoService.searchByID(id)));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAll() {
        List<TodoEntity> todoEntityList = this.todoService.searchAll();
        List<TodoResponse> response = todoEntityList.stream().map(TodoResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest todoRequest) {
        TodoEntity todoEntity = this.todoService.updateByID(id, todoRequest);
        return ResponseEntity.ok(new TodoResponse(todoEntity));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        this.todoService.removeByID(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        this.todoService.removeAll();
        return ResponseEntity.ok().build();
    }

}
