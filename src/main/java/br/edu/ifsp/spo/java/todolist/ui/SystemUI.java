package br.edu.ifsp.spo.java.todolist.ui;

import br.edu.ifsp.spo.java.todolist.model.Todo;

import java.util.List;

public interface SystemUI {

    void menuTodos(List<Todo> todos);
    void createTodo();
    void showMessage(String message);
    void changeTodo();
}
