package com.shdatalink.todo.view.controller;

import com.shdatalink.common.model.Todo;
import com.shdatalink.common.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/todos")
public class TodoViewController {
    @Autowired
    private TodoService todoService;

    @RequestMapping(method = GET)
    public List<Todo> listAll() {
        return todoService.list();
    }
}
