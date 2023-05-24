package dev.wson.start.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String URL = "jdbc:mariadb://localhost:3306/todoapp";
    private static final String USER = "root";
    private static final String PASS = "startdb";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão com o banco de dados", e);
        }
    }
    public static void closeConnection(Connection connection) {
        try {
            if(connection != null) connection.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao encerrar a conexão com o banco de dados", e);
        }
    }
}