package main.br.edu.ifsp.spo.java.todolist.connection;

import main.br.edu.ifsp.spo.java.todolist.core.Task;
import main.br.edu.ifsp.spo.java.todolist.connection.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDB {

    public void insert(Task task) {
        String sql = "INSERT INTO task (text, completed, change_date) VALUES (?, ?, ?)"; // Insere uma nova Task

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, task.getText()); // Testa inserir o texto
            statement.setBoolean(2, task.isCompleted()); // // Testa inserir o status
            statement.setString(3, task.getChangeDate().toString()); // // Testa inserir a data de alteração

            statement.executeUpdate(); // Executa a inserção
            System.out.println("Tarefa inserida");

        } catch (SQLException e) {
            System.out.println("Erro" + e.getMessage());
        }
    }

    public List<Task> findTasks() {
        List<Task> tasks = new ArrayList<>();
        String selectTasks = "SELECT * FROM task"; // Instrução SELECT pra verificar todas as tasks no bdd

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectTasks);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String text = resultSet.getString("text");
                boolean completed = resultSet.getBoolean("completed");
                String change_date = resultSet.getString("change_date");
                LocalDateTime changeDate = LocalDateTime.parse(change_date);

                Task t = new Task(text); // Cria a tarefa
                t.setCompleted(completed); // Atualiza status
                t.setText(text);           // Garante o texto
                // Setando a data manualmente já que o construtor define `now()`
                // Vamos fazer isso já já, mas por enquanto use o metodo abaixo ↓
                t.setChangeDate(changeDate);

                tasks.add(t);
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar tarefas: " + e.getMessage());
        }

        return tasks;
    }
}
