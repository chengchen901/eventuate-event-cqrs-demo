package com.shdatalink.todo.domain.snapshot;

import com.shdatalink.common.model.TodoInfo;
import com.shdatalink.todo.domain.TodoAggregate;
import io.eventuate.Snapshot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 成晨
 * @since 2024/3/25 17:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoSnapshot implements Snapshot {
    private String title;
    private Boolean completed;
    private Integer orderId;

    public TodoSnapshot(TodoAggregate todoAggregate) {
        TodoInfo todo = todoAggregate.getTodo();
        this.title = todo.getTitle();
        this.completed = todo.isCompleted();
        this.orderId = todo.getOrder();
    }
}
