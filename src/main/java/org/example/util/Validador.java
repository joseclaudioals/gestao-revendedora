package org.example.util;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.Year;

public final class Validador {
    private Validador() {} // bloqueia o construtor dentro da propria classe

    public static BigDecimal ValidarPreco(String n) throws InvalidParameterException {
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

    public static LocalDate ValidarDatas(int ano, int mes){
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

    public static int validarPositivos(int n){
        if (n > 0) {
            return n;
        } else {
            throw new InvalidParameterException("A quantidade de produtos deve ser mmaior que zero");
        }
    }
}
