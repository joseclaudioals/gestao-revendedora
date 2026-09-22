package org.example.model;

import org.example.util.Validador;

import java.math.BigDecimal;
import java.time.LocalDate; // API atual para lidar com tempo em Java

public class Lote {
    int id;
    BigDecimal precoUnitario;
    LocalDate dataValidade;
    LocalDate dataCompra;
    int quantidade;
    StatusLote status;
    Produto produto;

    Lote(String precoUnitario, int anoValidade, int mesValidade, int anoCompra, int mesCompra, int quantidade, Produto produto) {
        this.precoUnitario = Validador.validarPreco(precoUnitario);
        this.dataValidade = Validador.validarDatas(anoValidade, mesValidade);
        this.dataCompra = Validador.validarDatas(anoCompra, mesCompra);
        this.quantidade = Validador.validarPositivos(quantidade);
        this.produto = produto;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void setPrecoUnitario(String precoUnitario) {this.precoUnitario = Validador.validarPreco(precoUnitario);}
    private void setDataValidade(int ano, int mes) {this.dataValidade = Validador.validarDatas(ano, mes);}
    private void setDataCompra(int ano, int mes) {this.dataCompra = Validador.validarDatas(ano, mes);}
    private void setQuantidade(int quantidade) {this.quantidade = Validador.validarPositivos(quantidade);}
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getProduto() {return produto;}private void setStatus(StatusLote status) {this.status = status;}
    public int getId() {return id;}
    public BigDecimal getPrecoUnitario() {return precoUnitario;}
    public LocalDate getDataValidade() {return dataValidade;}
    public LocalDate getdataCompra() {return dataCompra;}
    public int getQuantidade() {return quantidade;}
    public StatusLote getStatus() {return status;}

    public enum StatusLote {
        DISPONIVEL, INDISPONIVEL, VENCIDO, PROMOCIONAL
    }
}
