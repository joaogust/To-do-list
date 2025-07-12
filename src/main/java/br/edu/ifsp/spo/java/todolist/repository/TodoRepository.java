package br.edu.ifsp.spo.java.todolist.repository;

import br.edu.ifsp.spo.java.todolist.model.Todo;
import br.edu.ifsp.spo.java.todolist.infra.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TodoRepository {


    public TodoRepository() {
        createTableIfNotExists();
    }

    public static void createTableIfNotExists() {  // Cria a tabela task
        String sql = """
            CREATE TABLE IF NOT EXISTS todo (
                   id INTEGER PRIMARY KEY AUTOINCREMENT,
                   text TEXT NOT NULL,
                   completed BOOLEAN NOT NULL,
                   change_date TEXT NOT NULL
            );
        """; // Colunas: pk, texto, status e data de alteração

        // Testa se a tabela task foi criada
        try (Connection connection = ConnectionDB.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Tabela todo criada");
        } catch (SQLException e) {
            System.out.println("Erro. Tabela todo não foi criada: " + e.getMessage());
        }
    }

    public void create(Todo todo) {
        String sql = "INSERT INTO todo (text, completed, change_date) VALUES (?, ?, ?)"; // Insere uma nova Task

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, todo.getText()); // Testa inserir o texto
            statement.setBoolean(2, todo.isCompleted()); // // Testa inserir o status
            statement.setString(3, todo.getChangeDate().toString()); // // Testa inserir a data de alteração

            statement.executeUpdate(); // Executa a inserção

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) { // Pega o próximo int pra pk
                todo.setId(generatedKeys.getInt(1));
            }

            System.out.println("Tarefa inserida!");

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void update(Todo todo) {
        String sql = "UPDATE todo SET completed = ?, change_date = ?, text = ? WHERE id = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, todo.isCompleted());
            statement.setString(2, todo.getChangeDate().toString());
            statement.setString(3, todo.getText());
            statement.setInt(4, todo.getId());

            int update = statement.executeUpdate(); // Executa a atualização

            if (update > 0 ) {
                System.out.println("Tarefa atualizada!");
            } else {
                System.out.println("Nenhuma tarefa foi atualizada");
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void delete(Todo todo) {
        String sql = "DELETE FROM todo WHERE id = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, todo.getId());
            statement.executeUpdate();
            System.out.println("Tarefa excluída!");

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public List<Todo> findAll() {
        List<Todo> todos = new ArrayList<>();
        String selectTasks = "SELECT * FROM todo"; // Instrução SELECT para verificar todas as tasks no bdd

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectTasks);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                boolean completed = resultSet.getBoolean("completed");
                String change_date = resultSet.getString("change_date");
                LocalDateTime changeDate = LocalDateTime.parse(change_date);

                Todo t = new Todo(text);
                t.setId(id);
                t.setCompleted(completed);
                t.setText(text);
                t.setChangeDate(changeDate);

                todos.add(t);
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar tarefas: " + e.getMessage());
        }

        return todos;
    }

    public List<Todo> findByStatus(boolean completed) {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todo WHERE completed = ?"; // Instrução SELECT para verificar todas as tasks no bdd

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, completed);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                boolean status = resultSet.getBoolean("completed");
                String change_date = resultSet.getString("change_date");
                LocalDateTime changeDate = LocalDateTime.parse(change_date);

                Todo t = new Todo(text);
                t.setId(id);
                t.setCompleted(status);
                t.setText(text);
                t.setChangeDate(changeDate);

                todos.add(t);
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar tarefas: " + e.getMessage());
        }

        return todos;
    }
}
