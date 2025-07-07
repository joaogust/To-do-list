package main.br.edu.ifsp.spo.java.todolist.core;

import java.util.ArrayList;

public class Page {
    private String name;
    private ArrayList<Task> tasks;

    public Page(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setName(String name) {
        this.name = name;
    }
}
