package com.shdatalink.common.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "com.shdatalink.todo.domain.TodoAggregate")
public interface TodoEvent extends Event {
}
