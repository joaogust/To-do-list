package main.br.edu.ifsp.spo.java.todolist;

import main.br.edu.ifsp.spo.java.todolist.connection.ConnectionDB;
import main.br.edu.ifsp.spo.java.todolist.connection.TaskDB;
import main.br.edu.ifsp.spo.java.todolist.core.Task;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ConnectionDB.createTableIfNotExists(); // Conexão com o banco de dados

        Task newTask = new Task("Estudar JDBC");
        TaskDB taskDB = new TaskDB();
        taskDB.insert(newTask);

        // Esses são testes pra ver se o SELECT funciona, depois a gente tira
        List<Task> allTasks = taskDB.findAllTasks();

        for (Task task : allTasks) {
            System.out.println("Id: " + task.getIdTask());
            System.out.println("Descrição: " + task.getText());
            System.out.println("Concluída: " + task.isCompleted());
            System.out.println("Data de alteração: " + task.getChangeDate());
            System.out.println("--------");
        }

        List<Task> pendingTasks = taskDB.findPending();
        for (Task task : pendingTasks) {
            System.out.println("Id: " + task.getIdTask());
            System.out.println("Descrição: " + task.getText());
            System.out.println("Concluída: " + task.isCompleted());
            System.out.println("Data de alteração: " + task.getChangeDate());
            System.out.println("--------");
        }

        List<Task> findCompleted = taskDB.findCompleted(); // Não deve exibir nada por enquanto, pois nenhuma task está completa
        for (Task task : findCompleted) {
            System.out.println("Id: " + task.getIdTask());
            System.out.println("Descrição: " + task.getText());
            System.out.println("Concluída: " + task.isCompleted());
            System.out.println("Data de alteração: " + task.getChangeDate());
            System.out.println("--------");
        }
    }
}