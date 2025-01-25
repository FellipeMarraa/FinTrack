package com.finTrack.model.finance.enums;

public enum FinanceTipo {

    RECEITA("Receita"),
    DESPESA("Despesa");

    private final String descricao;

    FinanceTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
