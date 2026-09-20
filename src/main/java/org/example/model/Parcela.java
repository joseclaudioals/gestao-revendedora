package org.example.model;

import org.example.util.Validador;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Parcela {
    private BigDecimal valorParcela;
    private BigDecimal valorPago;
    private LocalDate vencimento;
    private StatusParcela status;

    Parcela(String valorParcela, String valorPago, int anoVencimento, int mesVencimento){
        this.valorParcela = Validador.validarPreco(valorParcela);
        this.valorPago = Validador.validarPreco(valorPago);
        this.vencimento = Validador.validarDatas(anoVencimento, mesVencimento);
        this.status = StatusParcela.NAOPAGO;
    }

    public void setValorParcela(String valorParcela) {
        this.valorParcela = Validador.validarPreco(valorParcela);
    }

    public void setValorPago(String valorPago) {
        this.valorPago = Validador.validarPreco(valorPago);
    }

    public void setVencimento(int ano, int mes) {
        this.vencimento = Validador.validarDatas(ano, mes);
    }

    public void setStatus(StatusParcela status) {
        this.status = status;
    }

    public BigDecimal getValorParcela() {return valorParcela;}
    public BigDecimal getValorPago() {return valorPago;}
    public LocalDate getVencimento() {return vencimento;}
    public StatusParcela getStatus() {return status;}

    public enum StatusParcela{
        NAOPAGO, PAGO, ATRASADO
    }
}
