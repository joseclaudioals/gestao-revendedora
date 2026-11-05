package org.example.dao;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por fazer a autenticação do usuário no sistema
 */
public class AuthenticationDAO {
    /**
     * Método de suporte para verificação da existencia do usuário
     * @param username O nome do usuário a ser procurado no usuarios.db
     * @return caso a query retorne uma linha, a função retorna true
     * @throws SQLException caso falhe ao executar a query
     */
    public boolean usuarioExiste(String username) throws SQLException{
        String sql = "SELECT 1 FROM usuarios WHERE username = ?";
        try(Connection conn = MasterConnectionDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username); // substitui o primeiro ? por 'username'
            return stmt.executeQuery().next(); // pula a primeira linha da query (o cabeçalho da tabela)
        }
    }

    /**
     * Método para cadastrar um usuário no banco de dados e cria o banco do usuário com o padrão "username.db"
     * @param username O nome do usuário que será cadastrado no banco
     * @param senha A senha que foi "inputada" pelo usuário é convertida em hash, então, armazenada no banco
     * @return retorna verdadeiro caso o cadastro seja concluido, retorna falso caso o usuário já exista
     * @throws SQLException caso falhe ao executa
     */
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

    /**
     * Método responsável por realizar o login do usuário. O método busca o username no banco e verifica se os hash de senha batem
     * @param username nome do usuário no banco
     * @param senha a senha que o usuário tentará logar
     * @return verdadeiro caso os hash das senhas de banco e parametro baterem
     * @throws SQLException joga a exceção caso falhe na query
     */
    public boolean login(String username, String senha) throws  SQLException {
        String sql = "SELECT senha FROM usuarios WHERE username =?";
        try (Connection conn = MasterConnectionDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) return false;

            String hash = rs.getString("senha");
            // pega o hash do banco e verifica se a senha parametro possui o mesmo hash
            return BCrypt.checkpw(senha, hash);
        }
    }
}
