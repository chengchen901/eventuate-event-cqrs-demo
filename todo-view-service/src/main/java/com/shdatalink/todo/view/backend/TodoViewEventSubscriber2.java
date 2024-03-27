package com.shdatalink.todo.view.backend;

import cn.hutool.json.JSONUtil;
import com.shdatalink.common.event.TodoCreatedEvent;
import com.shdatalink.common.event.TodoDeletedEvent;
import com.shdatalink.common.event.TodoUpdatedEvent;
import com.shdatalink.common.model.Todo;
import com.shdatalink.common.service.TodoService;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EventSubscriber(id = "todoQuerySideEventHandlers2")
public class TodoViewEventSubscriber2 {

    @Autowired
    private TodoService todoService;

    @EventHandlerMethod
    public void create(DispatchedEvent<TodoCreatedEvent> de) {
        log.info("TodoViewEventSubscriber2 执行 create,参数:{}", JSONUtil.toJsonStr(de));
    }
}
