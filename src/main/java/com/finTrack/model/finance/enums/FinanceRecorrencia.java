package com.finTrack.model.finance.enums;

public enum FinanceRecorrencia {
    UNICO("Único"),
    PARCELADO("Parcelado"),
    FIXO("Fixo");

    private final String descricao;

    FinanceRecorrencia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
