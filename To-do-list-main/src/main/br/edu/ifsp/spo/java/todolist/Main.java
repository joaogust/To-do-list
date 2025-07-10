package main.br.edu.ifsp.spo.java.todolist;

import main.br.edu.ifsp.spo.java.todolist.connection.ConnectionDB;
import main.br.edu.ifsp.spo.java.todolist.connection.TaskDB;
import main.br.edu.ifsp.spo.java.todolist.core.Task;

import java.awt.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ConnectionDB.createTableIfNotExists(); // Conexão com o banco de dados

        Task newTask = new Task("Estudar JDBC");
        TaskDB taskDB = new TaskDB();



        List<Task> allTasks = taskDB.findTasks();
        // Esse é um teste pra ver se o SELECT funciona, depois a gente tira
        for (Task task : allTasks) {
            System.out.println("Descrição: " + task.getText());
            System.out.println("Concluída: " + task.isCompleted());
            System.out.println("Data de alteração: " + task.getChangeDate());
            System.out.println("--------");
        }
    }
}