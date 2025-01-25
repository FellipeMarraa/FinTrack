package com.finTrack.model.finance.enums;

public enum FinanceCategoria {
    ALIMENTACAO("Alimentação"),
    TRANSPORTE("Transporte"),
    SAUDE("Saúde"),
    EDUCACAO("Educação"),
    LAZER("Lazer"),
    INVESTIMENTOS("Investimentos"),
    MORADIA("Moradia"),
    UTILIDADES("Utilidades"),
    SALARIO("Salário"),
    OUTROS("Outros");

    private final String descricao;

    FinanceCategoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
