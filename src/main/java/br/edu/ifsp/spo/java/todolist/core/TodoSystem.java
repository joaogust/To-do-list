package br.edu.ifsp.spo.java.todolist.core;

import br.edu.ifsp.spo.java.todolist.model.Todo;
import br.edu.ifsp.spo.java.todolist.repository.TodoRepository;
import br.edu.ifsp.spo.java.todolist.ui.SystemUI;

import java.util.List;

public class TodoSystem {

    TodoRepository repository;
    private SystemUI ui;

    public TodoSystem(SystemUI ui) {
        this.repository = new TodoRepository();
        this.ui = ui;
    }

    public void menuAllTodos(){
        List<Todo> todos = repository.findAll();
        ui.menuTodos(todos);
    }

    public void menuCompletedTodos(){
        List<Todo> todos = repository.findByStatus(true);
        ui.menuTodos(todos);
    }

    public void menuPendingTodos(){
        List<Todo> todos = repository.findByStatus(false);
        ui.menuTodos(todos);
    }

    public void addTodo(String description) {
        Todo todo = new Todo(description);
        repository.create(todo);
    }

    public void deleteTodo(Todo todo) {
        repository.delete(todo);
    }

    public void changeTodo(Todo todo) {
        repository.update(todo);
    }

    public TodoRepository getRepository() {
        return repository;
    }
}
