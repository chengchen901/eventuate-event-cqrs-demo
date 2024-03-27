package com.shdatalink.todo.domain;

import cn.hutool.json.JSONUtil;
import com.shdatalink.common.event.TodoDeletionRequestedEvent;
import com.shdatalink.todo.command.DeleteTodoCommand;
import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@EventSubscriber(id = "todoCommandSideEventHandlers")
public class TodoEventSubscriber {
    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> deleteTodo(EventHandlerContext<TodoDeletionRequestedEvent> ctx) {
        log.info("TodoEventSubscriber 执行 deleteTodo,参数:{}", JSONUtil.toJsonStr(ctx));
        String todoId = ctx.getEvent().getTodoId();
        return ctx.update(TodoAggregate.class, todoId, new DeleteTodoCommand());
    }
}
