package org.example.util;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.Year;

public final class Validador {
    private Validador() {} // bloqueia o construtor dentro da propria classe

    public static BigDecimal validarPreco(String n) throws InvalidParameterException {
        //valida se a string possui apenas numeros e não inicia com zero
        String regex = "^[1-9]\\d*$";
        if (n.matches(regex)) {
            StringBuilder s = new StringBuilder(n);
            s.insert(s.length() - 2, ".");

            //O construtor do BigDecimal não aceita StringBuilder, convertemos então para string
            return new BigDecimal(s.toString().trim());
        }else{
            throw new InvalidParameterException("Parametro invalido: preço deve apenas possuir numeros");
        }
    }

    public static LocalDate validarDatas(int ano, int mes) throws InvalidParameterException{
        int anoAtual = Year.now().getValue();

        if (ano >= anoAtual) {
            if (mes >= 1 && mes <= 12) {
                return LocalDate.of(ano, mes, 1); // com of consigo converter int para data
            } else {
                throw new InvalidParameterException("Parametro invalido: deve ser um mes existente");
            }
        } else {
            throw new InvalidParameterException("Parametro invalido: deve ser um ano igual ou maior que o atual");
        }
    }

    public static int validarPositivos(int n) throws InvalidParameterException{
        if (n > 0) {
            return n;
        } else {
            throw new InvalidParameterException("A quantidade de produtos deve ser mmaior que zero");
        }
    }

    public static String validarTelefone(String telefone) throws InvalidParameterException{
        // Valida se o telefone possui 11 Numeros
        String regex = "^[0-9]{11}$";

        if(telefone.matches(regex)){
            String ddd = telefone.substring(0, 2);
            StringBuilder numeros = new StringBuilder(telefone.substring(2));
            numeros.insert(4, "-");

            return "("+ ddd + ") " + numeros;
        }else{
            throw new InvalidParameterException("O telefone só deve possuir 11 caracteres e somente numeros");
        }
    }

    public static String validarNome(String nome) throws InvalidParameterException{
        //Valida apenas nomes que não possuam:
        //Caracteres especiais;
        //Numeros;
        //Pontuação
        //Hifens são permitidos
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:[ '-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$";
        if(nome.matches(regex)) return nome;
        else {
            throw new InvalidParameterException("Nome não deve receber caracters especiais, pontos e numeros");
        }
    }

    public static String validarSenha(String senha){
        //Valida apenas nomes que:
        //Senha possui 8 ou mais caracteres
        //Possui pelo menos um caracter especial
        //Possui pelo menos um caracter numerico
        //Nao possui espaços
        if (!senha.matches("^.{9,}$")){
            throw new InvalidParameterException("A senha deve possuir 8 ou mais caracteres");
        }else if(!senha.matches("^(?=.*[!@#$%^&*(),.?\":{}|<>_\\-\\[\\]\\/\\\\~`+=;']).*$")){
            throw  new InvalidParameterException(("A senha deve possuir ao menos um caracter especial"));
        }else if(!senha.matches("^(?=.*\\d).*$")){
            throw new InvalidParameterException("A senha deve possuir ao menos um caracter numerico");
        }else if(!senha.matches("^\\S+$")){
            throw new InvalidParameterException("A senha nao deve possuir espaços");
        }else{
            return senha;
        }
    }
}
