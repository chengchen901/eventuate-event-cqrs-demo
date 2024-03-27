package com.shdatalink.todo.domain;


import cn.hutool.json.JSONUtil;
import com.shdatalink.common.event.TodoDeletionRequestedEvent;
import com.shdatalink.todo.command.DeleteTodosCommand;
import com.shdatalink.todo.command.TodoCommand;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class TodoBulkDeleteAggregate extends ReflectiveMutableCommandProcessingAggregate<TodoBulkDeleteAggregate, TodoCommand> {
    public List<Event> process(DeleteTodosCommand cmd) {
        log.info("TodoBulkDeleteAggregate 执行 process(DeleteTodosCommand cmd),参数:{},当前对象:{}", JSONUtil.toJsonStr(cmd), this);
        return cmd.getIds()
                .stream()
                .map(TodoDeletionRequestedEvent::new)
                .collect(Collectors.toList());
    }

    public void apply(TodoDeletionRequestedEvent event) {
        log.info("TodoBulkDeleteAggregate 执行 apply(TodoDeletionRequestedEvent event),参数:{},当前对象:{}", JSONUtil.toJsonStr(event), this);
    }
}
