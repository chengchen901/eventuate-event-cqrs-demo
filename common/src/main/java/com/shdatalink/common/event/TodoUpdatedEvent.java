package com.shdatalink.common.event;


import com.shdatalink.common.model.TodoInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdatedEvent implements TodoEvent {
    private TodoInfo todo;
}