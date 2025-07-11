package main.br.edu.ifsp.spo.java.todolist.connection;

import main.br.edu.ifsp.spo.java.todolist.core.Task;

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
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, task.getText()); // Testa inserir o texto
            statement.setBoolean(2, task.isCompleted()); // // Testa inserir o status
            statement.setString(3, task.getChangeDate().toString()); // // Testa inserir a data de alteração

            statement.executeUpdate(); // Executa a inserção

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) { // Pega o próximo int pra pk
                task.setIdTask(generatedKeys.getInt(1));
            }

            System.out.println("Tarefa inserida");

        } catch (SQLException e) {
            System.out.println("Erro" + e.getMessage());
        }
    }

    public List<Task> findAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String selectTasks = "SELECT * FROM task"; // Instrução SELECT pra verificar todas as tasks no bdd

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectTasks);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idTask = resultSet.getInt("id_task");
                String text = resultSet.getString("text");
                boolean completed = resultSet.getBoolean("completed");
                String change_date = resultSet.getString("change_date");
                LocalDateTime changeDate = LocalDateTime.parse(change_date);

                Task t = new Task(idTask, text, completed, changeDate); // Cria a tarefa
                tasks.add(t);
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar tarefas: " + e.getMessage());
        }

        return tasks;
    }

    public List<Task> findPending() { // Um SELECT pra filtrar as tabelas com completed FALSE
        List<Task> tasks = new ArrayList<>();
        String pendingTasks = "SELECT * FROM task WHERE completed = false"; // WHERE completed = false

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement statement = conn.prepareStatement(pendingTasks);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idTask = resultSet.getInt("id_task");
                String text = resultSet.getString("text");
                boolean completed = resultSet.getBoolean("completed");
                String change_date = resultSet.getString("change_date");
                LocalDateTime changeDate = LocalDateTime.parse(change_date);

                Task t = new Task(idTask, text, completed, changeDate); // Cria a tarefa
                tasks.add(t);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar tarefas pendentes: " + e.getMessage());
        }
        return tasks;
    }

    public List<Task> findCompleted() { // Um SELECT pra filtrar as tabelas com completed TRUE
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task WHERE completed = true"; // WHERE completed = true

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idTask = resultSet.getInt("id_task");
                String text = resultSet.getString("text");
                boolean completed = resultSet.getBoolean("completed");
                String change_date = resultSet.getString("change_date");
                LocalDateTime changeDate = LocalDateTime.parse(change_date);

                Task t = new Task(idTask, text, completed, changeDate); // Cria a tarefa
                t.setCompleted(completed);
                t.setChangeDate(changeDate);
                tasks.add(t);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar tarefas concluídas: " + e.getMessage());
        }
        return tasks;
    }
}
