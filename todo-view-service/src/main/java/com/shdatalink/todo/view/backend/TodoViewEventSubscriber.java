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
@EventSubscriber(id = "todoQuerySideEventHandlers")
public class TodoViewEventSubscriber {

    @Autowired
    private TodoService todoService;

    /**
     * 注意：如果某个 EventHandlerMethod 执行失败抛出异常，则该 EventSubscriber 会关闭消息订阅，导致其他 EventHandlerMethod 也无法接受消息
     */
    @EventHandlerMethod
    public void create(DispatchedEvent<TodoCreatedEvent> de) {
        log.info("TodoViewEventSubscriber 执行 create,参数:{}", JSONUtil.toJsonStr(de));
        Todo todo = new Todo(de.getEvent().getTodo());
        todo.setId(de.getEntityId());
        todoService.save(todo);
    }

    // 同一个 EventSubscriber 下的 Event 类型不能相同，否则启动项目会报错
//    @EventHandlerMethod
//    public void create2222(DispatchedEvent<TodoCreatedEvent> de) {
//        log.info("TodoViewEventSubscriber 执行 create222,参数:{}", JSONUtil.toJsonStr(de));
//    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<TodoDeletedEvent> de) {
        log.info("TodoViewEventSubscriber 执行 delete,参数:{}", JSONUtil.toJsonStr(de));
        todoService.removeById(de.getEntityId());
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<TodoUpdatedEvent> de) {
        log.info("TodoViewEventSubscriber 执行 update,参数:{}", JSONUtil.toJsonStr(de));
        Todo todo = new Todo(de.getEvent().getTodo());
        todo.setId(de.getEntityId());
        todoService.updateById(todo);
    }
}
