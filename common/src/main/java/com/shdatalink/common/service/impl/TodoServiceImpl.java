package com.shdatalink.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shdatalink.common.mapper.TodoMapper;
import com.shdatalink.common.model.Todo;
import com.shdatalink.common.service.TodoService;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements TodoService {
}
