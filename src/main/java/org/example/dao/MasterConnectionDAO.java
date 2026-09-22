package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MasterConnectionDAO {
    private static final String url = "jdbc:sqlite:data.usuarios.db";

    // ao usar conn é bom usar try para que ele abra a feche a conexão com o banco automaticamente
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url);

        String sql = """
                    CREATE TABLE IF NOT EXISTS usuarios(
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        username TEXT UNIQUE NOT NULL,
                        senha TEXT NOT NULL
                    )
        """;
        conn.createStatement().execute(sql);
        return conn;
    }

}
