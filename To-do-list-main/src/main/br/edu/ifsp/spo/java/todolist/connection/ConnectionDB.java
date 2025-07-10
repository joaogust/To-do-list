package main.br.edu.ifsp.spo.java.todolist.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB { // Classe pra conectar com o bdd

    private static final String DB_URL = "jdbc:sqlite:tasks.db"; // Caminho do arquivo do banco de dados

    // Retorna uma conexão com o banco
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL); // Usa o caminho do arquivo
    }

    // Usa na classe Main
    public static void createTableIfNotExists() {  // Cria a tabela task
        String sql = """
            CREATE TABLE IF NOT EXISTS task (
                   id_task INTEGER PRIMARY KEY AUTOINCREMENT,
                   text TEXT NOT NULL,
                   completed BOOLEAN NOT NULL,
                   change_date TEXT NOT NULL
            );
        """; // Colunas: pk, texto, status e data de alteração

        // Testar se a tabela task foi criada
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Tabela task criada");
        } catch (SQLException e) {
            System.out.println("Erro. Tabela task não foi criada: " + e.getMessage());
        }
    }
}
