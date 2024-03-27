package com.shdatalink.todo.domain;


import cn.hutool.json.JSONUtil;
import com.shdatalink.common.event.TodoCreatedEvent;
import com.shdatalink.common.event.TodoDeletedEvent;
import com.shdatalink.common.event.TodoUpdatedEvent;
import com.shdatalink.common.model.TodoInfo;
import com.shdatalink.todo.command.CreateTodoCommand;
import com.shdatalink.todo.command.DeleteTodoCommand;
import com.shdatalink.todo.command.TodoCommand;
import com.shdatalink.todo.command.UpdateTodoCommand;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class TodoAggregate extends ReflectiveMutableCommandProcessingAggregate<TodoAggregate, TodoCommand> {

    private TodoInfo todo;
    private boolean deleted;

    public List<Event> process(CreateTodoCommand cmd) {
        log.info("TodoAggregate 执行 process(CreateTodoCommand cmd),参数:{},当前对象:{}", JSONUtil.toJsonStr(cmd), this);
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new TodoCreatedEvent(cmd.getTodo()));
    }

    public List<Event> process(UpdateTodoCommand cmd) {
        log.info("TodoAggregate 执行 process(UpdateTodoCommand cmd),参数:{},当前对象:{}", JSONUtil.toJsonStr(cmd), this);
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new TodoUpdatedEvent(cmd.getTodo()));
    }

    public List<Event> process(DeleteTodoCommand cmd) {
        log.info("TodoAggregate 执行 process(DeleteTodoCommand cmd),当前对象:{}", this);
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new TodoDeletedEvent());
    }


    public void apply(TodoCreatedEvent event) {
        log.info("TodoAggregate 执行 apply(TodoCreatedEvent event),参数:{},当前对象:{}", JSONUtil.toJsonStr(event), this);
        this.todo = event.getTodo();
    }

    public void apply(TodoUpdatedEvent event) {
        log.info("TodoAggregate 执行 apply(TodoUpdatedEvent event),参数:{},当前对象:{}", JSONUtil.toJsonStr(event), this);
        this.todo = event.getTodo();
    }

    public void apply(TodoDeletedEvent event) {
        log.info("TodoAggregate 执行 apply(TodoDeletedEvent event),当前对象:{}", this);
        this.deleted = true;
    }

}
