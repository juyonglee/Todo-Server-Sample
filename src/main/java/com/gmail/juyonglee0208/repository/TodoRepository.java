package com.gmail.juyonglee0208.repository;

import com.gmail.juyonglee0208.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoModel, Long> {

}
