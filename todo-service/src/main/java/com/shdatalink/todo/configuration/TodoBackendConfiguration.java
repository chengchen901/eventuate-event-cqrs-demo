package com.shdatalink.todo.configuration;

import com.shdatalink.todo.command.TodoCommand;
import com.shdatalink.todo.domain.TodoAggregate;
import com.shdatalink.todo.domain.TodoBulkDeleteAggregate;
import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoBackendConfiguration {
    @Bean
    public AggregateRepository<TodoAggregate, TodoCommand> aggregateRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(TodoAggregate.class, eventStore);
    }

    @Bean
    public AggregateRepository<TodoBulkDeleteAggregate, TodoCommand> bulkDeleteAggregateRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(TodoBulkDeleteAggregate.class, eventStore);
    }
}
