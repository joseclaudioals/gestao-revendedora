package org.example.model;

import org.example.util.Validador;

public class Usuario {
    private String nome;
    private String senha;

    Usuario(String nome, String senha){
        this.nome = Validador.validarNome(nome);
        this.senha = Validador.validarSenha(senha);
    }
    public String getName(){return nome;}

    private void setSenha(String senha) {
        this.senha = Validador.validarSenha(senha);
    }
    private void setNome(String nome) {
        this.nome = Validador.validarNome(nome);
    }
}
