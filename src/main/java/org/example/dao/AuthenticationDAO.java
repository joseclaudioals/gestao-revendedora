package org.example.dao;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDAO {
    public boolean usuarioExiste(String username) throws SQLException{
        String sql = "SELECT 1 FROM usuarios WHERE username = ?";
        try(Connection conn = MasterConnectionDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username); // substitui o primeiro ? por 'username'
            return stmt.executeQuery().next(); // pula a primeira linha da query (o cabeçalho da tabela)
        }
    }

    public boolean cadastrar(String username, String senha) throws SQLException{
        if(usuarioExiste(username)) {return false;}

        String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());
        String sql = "INSERT INTO usuarios (username, senha) VALUES (?, ?)";

        try(Connection conn = MasterConnectionDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, username);
            stmt.setString(2, senhaHash);
            stmt.executeUpdate();
        }

        PerfilDAO.criarBanco(username);
        return true;
    }

    public boolean login(String username, String senha) throws  SQLException {
        String sql = "SELECT senha FROM usuarios WHERE username =?";
        try (Connection conn = MasterConnectionDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) return false;

            String hash = rs.getString("senha");
            return BCrypt.checkpw(senha, hash);
        }
    }
}
