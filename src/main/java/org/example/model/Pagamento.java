package org.example.model;

import org.example.util.Validador;

import java.util.ArrayList;
import java.util.List;

public class Pagamento {
    private String formaPagamento;
    private int qntParcelas;
    private boolean status; // todas as parcelas foram pagas ou não
    private List<Parcela> parcelas;

    Pagamento(String formaPagamento, int qntParcelas, boolean status){
        this.formaPagamento = Validador.validarNome(formaPagamento);
        this.qntParcelas = Validador.validarPositivos(qntParcelas);
        this.status = false;
        this.parcelas = new ArrayList<>();
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = Validador.validarNome(formaPagamento);
    }

    public void setQntParcelas(int qntParcelas) {
        this.qntParcelas = Validador.validarPositivos(qntParcelas);
    }
    // deve ser positivo apenas quanto todas as parcelas são pagas
    public void setStatus(boolean status) {
        this.status = status;
    }
    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public String getFormaPagamento() {return formaPagamento;}
    public int getQntParcelas() {return qntParcelas;}
    public boolean isStatus() {return status;}
    public List<Parcela> getParcelas() {return parcelas;}
}
