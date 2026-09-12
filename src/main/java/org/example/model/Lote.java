package org.example.model;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDate; // API atual para lidar com tempo em Java
import java.time.Year;

public class Lote {
    int id;
    BigDecimal precoUnitario;
    LocalDate dataValidade;
    LocalDate dataCompra;
    int quantidade;
    StatusLote status;
    Produto produto;

    Lote(String precoUnitario, int anoValidade, int mesValidade, int anoCompra, int mesCompra, int quantidade){
        setPrecoUnitario(precoUnitario);
        setDataValidade(anoValidade, mesValidade);
        setDataCompra(anoCompra, mesCompra);
        setQuantidade(quantidade);
    }

    public void setId(int id) {
        this.id = id;
    }

    private void setPrecoUnitario(String precoUnitario) {
        //valida se a string possui apenas numeros e não inicia com zero
        String regex = "^[1-9]\\d*$";
        if(precoUnitario.matches(regex)){
            StringBuilder s = new StringBuilder(precoUnitario);
            s.insert(s.length()-2, ".");

            //O construtor do BigDecimal não aceita StringBuilder, convertemos então para string
            this.precoUnitario = new BigDecimal(s.toString());
        }

    }

    private void setDataValidade(int ano, int mes) {
        int anoAtual = Year.now().getValue();

        if(ano >= anoAtual){
            if(mes >=1 && mes <= 12){
                this.dataValidade = LocalDate.of(ano, mes, 1); // com of consigo converter int para data
            }else{
                throw new InvalidParameterException("Mes invalido");
            }
        }else{
            throw new InvalidParameterException("Ano invalido");
        }
    }

    private void setDataCompra(int ano, int mes) {
        int anoAtual = Year.now().getValue();

        if(ano >= anoAtual){
            if(mes >=1 && mes <= 12){
                this.dataValidade = LocalDate.of(ano, mes, 1); // com of consigo converter int para data
            }else{
                throw new InvalidParameterException("Mes invalido");
            }
        }else{
            throw new InvalidParameterException("Ano invalido");
        }
    }

    private void setQuantidade(int quantidade) {
        if(quantidade > 0){
            this.quantidade = quantidade;
        }else{
            throw new InvalidParameterException("A quantidade de produtos deve ser mmaior que zero");
        }
    }

    private void setStatus(StatusLote status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public LocalDate getdataCompra() {
        return dataCompra;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public StatusLote getStatus() {
        return status;
    }

    public enum StatusLote{
        DISPONIVEL, INDISPONIVEL, VENCIDO, PROMOCIONAL
    }
}
