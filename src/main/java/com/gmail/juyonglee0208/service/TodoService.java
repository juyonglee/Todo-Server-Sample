package com.gmail.juyonglee0208.service;

import com.gmail.juyonglee0208.model.TodoEntity;
import com.gmail.juyonglee0208.model.TodoRequest;
import com.gmail.juyonglee0208.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoEntity add(TodoRequest request) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setOrder(request.getOrder());
        todoEntity.setCompleted(request.getCompleted());
        return this.todoRepository.save(todoEntity);
    }

    public TodoEntity searchByID(long id) {
        return this.todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)) ;
    }

    public List<TodoEntity> searchAll() {
        return this.todoRepository.findAll();
    }

    public TodoEntity updateByID(Long id, TodoRequest todoRequest) {
        TodoEntity todoEntity = this.searchByID(id);
        if(todoRequest.getTitle() != null) {
            todoEntity.setTitle(todoRequest.getTitle());
        }
        if(todoRequest.getOrder() != null) {
            todoEntity.setOrder(todoRequest.getOrder());
        }
        if(todoRequest.getCompleted() != null) {
            todoEntity.setCompleted(todoRequest.getCompleted());
        }
        return todoEntity;
    }

    public void removeByID(Long id) {
        this.todoRepository.deleteById(id);
    }

    public void removeAll() {
        this.todoRepository.deleteAll();
    }

}
