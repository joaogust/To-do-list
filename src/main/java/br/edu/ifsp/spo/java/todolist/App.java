package br.edu.ifsp.spo.java.todolist;

import br.edu.ifsp.spo.java.todolist.core.TodoSystem;
import br.edu.ifsp.spo.java.todolist.ui.JavaFXUI;

public class App {

    public static void main(String[] args) {

        TodoSystem system = new TodoSystem(new JavaFXUI());
    }
}
