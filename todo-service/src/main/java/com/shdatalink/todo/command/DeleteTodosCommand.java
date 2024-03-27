package com.shdatalink.todo.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTodosCommand implements TodoCommand {
    private List<String> ids;
}
