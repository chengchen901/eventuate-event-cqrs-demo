package com.shdatalink.todo.domain;

import com.shdatalink.common.model.TodoInfo;
import com.shdatalink.todo.command.*;
import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.sync.AggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoAggregateService {
    @Autowired
    private AggregateRepository<TodoAggregate, TodoCommand> aggregateRepository;
    @Autowired
    private AggregateRepository<TodoBulkDeleteAggregate, TodoCommand> bulkDeleteAggregateRepository;

    public EntityWithIdAndVersion<TodoAggregate> save(TodoInfo todo) {
        return aggregateRepository.save(new CreateTodoCommand(todo));
    }

    public EntityWithIdAndVersion<TodoAggregate> remove(String id) {
        return aggregateRepository.update(id, new DeleteTodoCommand());
    }

    public EntityWithIdAndVersion<TodoAggregate> update(String id, TodoInfo newTodo) {
        return aggregateRepository.update(id, new UpdateTodoCommand(id, newTodo));
    }

    public EntityWithIdAndVersion<TodoBulkDeleteAggregate> deleteAll(List<String> ids) {
        return bulkDeleteAggregateRepository.save(new DeleteTodosCommand(ids));
    }
}
