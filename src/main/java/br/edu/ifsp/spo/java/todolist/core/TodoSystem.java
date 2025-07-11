package br.edu.ifsp.spo.java.todolist.core;

import br.edu.ifsp.spo.java.todolist.repository.TodoRepository;
import br.edu.ifsp.spo.java.todolist.ui.SystemUI;

public class TodoSystem {

    TodoRepository repository;
    private SystemUI ui;

    public TodoSystem(SystemUI ui) {
        this.repository = new TodoRepository();
        this.ui = ui;
        this.initialize();
    }

    private void initialize() {

    }
}
