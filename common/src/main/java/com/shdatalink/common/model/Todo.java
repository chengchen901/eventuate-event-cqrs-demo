package com.shdatalink.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("todo")
public class Todo {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String title;
    private Boolean completed;
    private Integer orderId;

    public Todo(TodoInfo todoInfo) {
        this.title = todoInfo.getTitle();
        this.completed = todoInfo.isCompleted();
        this.orderId = todoInfo.getOrder();
    }
}
