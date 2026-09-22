package org.example.model;

import org.example.util.Validador;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Produto {
    private String nome;
    private BigDecimal precoVarejo;
    private String codProduto;
    private String linhaProduto;
    private String descricao;
    private List<Lote> lotes;

    Produto(String nome, String precoVarejo, String codProduto, String linhaProduto, String descricao){
        this.nome = Validador.validarNome(nome);
        this.precoVarejo = Validador.validarPreco(precoVarejo);
        this.codProduto = codProduto; 
        this.linhaProduto = Validador.validarNome(linhaProduto);
        this.descricao = descricao; /
        this.lotes = new ArrayList<>();
    }

    public void setNome(String nome) {this.nome = Validador.validarNome(nome);}
    public void setPrecoVarejo(String precoVarejo) {this.precoVarejo = Validador.validarPreco(precoVarejo);}
    public void setCodProduto(String codProduto) {this.codProduto = codProduto;}
    public void setLinhaProduto(String linhaProduto) {this.linhaProduto = Validador.validarNome(linhaProduto);}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public String getNome() {return nome;}
    public BigDecimal getPrecoVarejo() {return precoVarejo;}
    public String getCodProduto() {return codProduto;}
    public String getLinhaProduto() {return linhaProduto;}
    public String getDescricao() {return descricao;}
}
