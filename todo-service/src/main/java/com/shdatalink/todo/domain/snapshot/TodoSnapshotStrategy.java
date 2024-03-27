package com.shdatalink.todo.domain.snapshot;

import com.shdatalink.common.model.TodoInfo;
import com.shdatalink.todo.command.CreateTodoCommand;
import com.shdatalink.todo.domain.TodoAggregate;
import io.eventuate.*;
import io.eventuate.common.id.Int128;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class TodoSnapshotStrategy extends AbstractSnapshotStrategy {
    @Override
    public Class<?> getAggregateClass() {
        log.info("TodoSnapshotStrategy 执行 getAggregateClass");
        return TodoAggregate.class;
    }

    @Override
    protected int getSnapshotThreshold() {
        return 3;
    }

    @Override
    protected Snapshot generateSnapshot(Aggregate aggregate, Optional<Int128> snapshotVersion, List<EventWithMetadata> oldEvents, List<Event> newEvents) {
        TodoAggregate todo = (TodoAggregate) aggregate;
        TodoSnapshot snapshot = new TodoSnapshot(todo);
        log.info("TodoSnapshotStrategy 执行 generateSnapshot，Aggregate:{},TodoSnapshot:{}", todo, snapshot);
        return snapshot;
    }

    @Override
    public Aggregate recreateAggregate(Class<?> clasz, Snapshot snapshot, MissingApplyEventMethodStrategy missingApplyEventMethodStrategy) {
        TodoSnapshot todoSnapshot = (TodoSnapshot) snapshot;
        TodoInfo todo = new TodoInfo();
        todo.setTitle(todoSnapshot.getTitle());
        todo.setOrder(todoSnapshot.getOrderId());
        todo.setCompleted(todoSnapshot.getCompleted());

        TodoAggregate aggregate = new TodoAggregate();
        List<Event> events = aggregate.process(new CreateTodoCommand(todo));
        Aggregates.applyEventsToMutableAggregate(aggregate, events, missingApplyEventMethodStrategy);
        log.info("TodoSnapshotStrategy 执行 recreateAggregate，snapshot:{},TodoAggregate:{}", todoSnapshot, aggregate);
        return aggregate;
    }
}
