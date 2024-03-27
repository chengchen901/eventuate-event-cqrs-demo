package com.shdatalink.todo.command;


import com.shdatalink.common.model.TodoInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTodoCommand implements TodoCommand {
    private TodoInfo todo;
}
