package com.shdatalink.todo.controller;

import com.shdatalink.common.mapper.TodoMapper;
import com.shdatalink.common.model.Todo;
import com.shdatalink.common.model.TodoInfo;
import com.shdatalink.common.service.TodoService;
import com.shdatalink.todo.domain.TodoAggregate;
import com.shdatalink.todo.domain.TodoAggregateService;
import io.eventuate.EntityWithIdAndVersion;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RestController
@RequestMapping(value = "/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoAggregateService todoAggregateService;
    @Autowired
    private TodoMapper todoMapper;

    @RequestMapping(method = POST)
    public TodoInfo saveTodo(@RequestBody TodoInfo todo) {
        log.info("TodoController 开始执行 saveTodo");
        TodoInfo todoInfo = toResource(todoAggregateService.save(todo));
        log.info("TodoController 执行完成 saveTodo");
        return todoInfo;
    }

    @RequestMapping(value = "/{todo-id}", method = DELETE)
    public TodoInfo deleteOneTodo(@PathVariable("todo-id") String id, HttpServletRequest request) {
        return toResource(todoAggregateService.remove(id));
    }

    @RequestMapping(method = DELETE)
    public void deleteAllTodos() {
        List<Todo> todosToDelete = todoMapper.selectList(null);
        if (todosToDelete.size() > 0) {
            todoAggregateService.deleteAll(todosToDelete
                    .stream()
                    .map(Todo::getId)
                    .collect(Collectors.toList()));
        }
    }

    @RequestMapping(value = "/{todo-id}", method = PATCH, headers = {"Content-type=application/json"})
    public TodoInfo updateTodo(@PathVariable("todo-id") String id, @RequestBody TodoInfo newTodo, HttpServletRequest request) {
        return toResource(todoAggregateService.update(id, newTodo));
    }

    @RequestMapping(value = "/{todo-id}", method = GET)
    public Todo getTodo(@PathVariable("todo-id") String id) {
        return todoService.getById(id);
    }

    private TodoInfo toResource(EntityWithIdAndVersion<TodoAggregate> e) {
        log.info("EntityWithIdAndVersion:{}", e);
        return e.getAggregate().getTodo();
    }
}
