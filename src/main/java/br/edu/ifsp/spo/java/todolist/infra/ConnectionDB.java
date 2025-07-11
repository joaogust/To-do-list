package br.edu.ifsp.spo.java.todolist.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String DB_URL = "jdbc:sqlite:tasks.db"; // Caminho do arquivo do banco de dados

    // Retorna uma conexão com o banco
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL); // Usa o caminho do arquivo
    }
}