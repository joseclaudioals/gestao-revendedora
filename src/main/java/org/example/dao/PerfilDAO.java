package org.example.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PerfilDAO {
    private static String CaminhoBanco(String username) {
        return "jdbc:sqlite:data/perfil/" + username + ".db";
    }

    public static void criarBanco(String username) throws SQLException{
        File pasta = new File("data/perfis");
        if(!pasta.exists()) pasta.mkdirs(); // Se a pasta nao existir, criamos uma

        try (Connection conn = DriverManager.getConnection(CaminhoBanco(username))){ // procura o driver do banco para se conectar na sessão do usuário
            String sql = """
                    CREATE TABLE IF NOT EXISTS perfil(
                        id INTEGER PRIMARY KEY AUTOINCREMENT.
                        nome TEXT,
                    )
                    """;
            conn.createStatement().execute(sql);

        }
    }

    public static Connection getConexaoUsuario(String username) throws SQLException{
        return DriverManager.getConnection(CaminhoBanco(username))
    }
}
