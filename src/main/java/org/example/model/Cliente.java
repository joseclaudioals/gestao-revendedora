package org.example.model;

import java.security.InvalidParameterException;

public class Cliente {
    private String nome;
    private String telefone;
    private String endereco;
    private StatusCliente status;

    Cliente(String nome, String telefone, String endereco){
        setNome(nome);
        setTelefone(telefone);
        setEndereco(endereco);
        status = StatusCliente.PADRAO;
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

    public StatusCliente getStatus() {
        return status;
    }

    public void setNome(String nome) {
        //Valida apenas nomes que não possuam:
        //Caracteres especiais;
        //Numeros;
        //Pontuação
        //Hifens são permitidos
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:[ '-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$";
        if(nome.matches(regex)) this.nome = nome;
        else {
            throw new InvalidParameterException("Nome não deve receber caracters especiais, pontos e numeros");
        }
    }

    public void setTelefone(String telefone) {
        // Valida se o telefone possui 11 Numeros
        String regex = "^[0-9]{11}$";

        if(telefone.matches(regex)){
            String ddd = telefone.substring(0, 2);
            StringBuilder numeros = new StringBuilder(telefone.substring(2));
            numeros.insert(4, "-");

            this.telefone = "("+ ddd + ") " + numeros.toString();
        }else{
            throw new InvalidParameterException("O telefone só deve possuir 11 caracteres e somente numeros");
        }
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setStatus(StatusCliente status) {
        this.status = status;
    }

    public enum StatusCliente{
        PADRAO, BOM, RUIM
    }
}


