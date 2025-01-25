package com.finTrack.model.finance.financialGoal;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class SubGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;  // Descrição da submeta
    private BigDecimal valor;  // Valor da submeta

    @ManyToOne
    private FinancialGoal goal;  // Meta a qual esta submeta pertence

    public SubGoal() {}

    // Getters e Setters

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}