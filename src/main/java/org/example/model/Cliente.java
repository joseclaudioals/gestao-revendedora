package org.example.model;

import org.example.util.Validador;

public class Cliente {
    private String nome;
    private String telefone;
    private String endereco;
    private StatusCliente status;

    Cliente(String nome, String telefone, String endereco){
        this.nome = Validador.validarNome(nome);
        this.telefone = Validador.validarTelefone(telefone);
        this.endereco = endereco;
        this.status = StatusCliente.PADRAO;
    }

    public String getNome() {
        return nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getEndereco() {
        return endereco;
    }
    public StatusCliente getStatus() {return status;}

    public void setNome(String nome) {this.nome = Validador.validarNome(nome);}
    public void setTelefone(String telefone) {this.telefone = Validador.validarTelefone(telefone);}
    public void setEndereco(String endereco) {this.endereco = endereco;}
    public void setStatus(StatusCliente status) {
        this.status = status;
    }

    public enum StatusCliente{
        PADRAO, BOM, RUIM
    }
}


