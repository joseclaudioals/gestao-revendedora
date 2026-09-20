package org.example.model;

import org.example.util.Validador;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Venda {
    private LocalDate dataCompra;
    private BigDecimal totalCompra;
    ArrayList<Produto> produtosComprados;

    Venda(int mesDataCompra, int anoDataCompra, String totalCompra){
        this.dataCompra = Validador.validarDatas(anoDataCompra, mesDataCompra);
        this.totalCompra = Validador.validarPreco(totalCompra);
        this.produtosComprados = new ArrayList<>();
    }

    public LocalDate getDataCompra() {return dataCompra;}
    public BigDecimal getTotalCompra() {return totalCompra;}

    public void setDataCompra(int mesDataCompra, int anoDataCompra) {
        this.dataCompra = Validador.validarDatas(anoDataCompra, mesDataCompra);
    }

    public void setTotalCompra(String totalCompra) {
        this.totalCompra = Validador.validarPreco(totalCompra);
    }
}
