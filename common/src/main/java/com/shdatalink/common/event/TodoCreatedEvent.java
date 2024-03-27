package com.shdatalink.common.event;


import com.shdatalink.common.model.TodoInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoCreatedEvent implements TodoEvent {
    private TodoInfo todo;
}