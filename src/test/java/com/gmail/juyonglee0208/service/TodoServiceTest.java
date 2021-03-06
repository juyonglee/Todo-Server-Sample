package com.gmail.juyonglee0208.service;

import com.gmail.juyonglee0208.model.TodoModel;
import com.gmail.juyonglee0208.model.TodoRequest;
import com.gmail.juyonglee0208.repository.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void add() {
        when(this.todoRepository.save(any(TodoModel.class))).then(AdditionalAnswers.returnsFirstArg());

        TodoRequest expected = new TodoRequest();
        expected.setTitle("Test Title");
        TodoModel actual = this.todoService.add(expected);

        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    void searchByID() {
        TodoModel todoModel = new TodoModel();
        todoModel.setId(123L);
        todoModel.setTitle("Title");
        todoModel.setOrder(0L);
        todoModel.setCompleted(false);
        Optional<TodoModel> optional = Optional.of(todoModel);

        given(this.todoRepository.findById(anyLong())).willReturn(optional);

        TodoModel actual = this.todoService.searchByID(123L);
        TodoModel expected = optional.get();

        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getOrder(), actual.getOrder());
        Assertions.assertEquals(expected.getCompleted(), actual.getCompleted());
    }

    @Test
    public void searchByIdFailed() {
        given(this.todoRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResponseStatusException.class, ()->{
            this.todoService.searchByID(123L);
        });
    }
}