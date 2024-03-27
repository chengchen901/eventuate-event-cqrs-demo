package com.shdatalink.common.event;


import io.eventuate.Event;
import io.eventuate.EventEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EventEntity(entity = "com.shdatalink.todo.domain.TodoBulkDeleteAggregate")
public class TodoDeletionRequestedEvent implements Event {
    private String todoId;
}
