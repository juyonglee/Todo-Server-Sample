package com.gmail.juyonglee0208.service;

import com.gmail.juyonglee0208.model.TodoModel;
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

    public TodoModel add(TodoRequest request) {
        TodoModel todoModel = new TodoModel();
        todoModel.setTitle(request.getTitle());
        todoModel.setOrder(request.getOrder());
        todoModel.setCompleted(request.getCompleted());
        return this.todoRepository.save(todoModel);
    }

    public TodoModel searchByID(long id) {
        return this.todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)) ;
    }

    public List<TodoModel> searchAll() {
        return this.todoRepository.findAll();
    }

    public TodoModel updateByID(Long id, TodoRequest todoRequest) {
        TodoModel todoModel = this.searchByID(id);
        if(todoRequest.getTitle() != null) {
            todoModel.setTitle(todoRequest.getTitle());
        }
        if(todoRequest.getOrder() != null) {
            todoModel.setOrder(todoRequest.getOrder());
        }
        if(todoRequest.getCompleted() != null) {
            todoModel.setCompleted(todoRequest.getCompleted());
        }
        return this.todoRepository.save(todoModel);
    }

    public void removeByID(Long id) {
        this.todoRepository.deleteById(id);
    }

    public void removeAll() {
        this.todoRepository.deleteAll();
    }

}
