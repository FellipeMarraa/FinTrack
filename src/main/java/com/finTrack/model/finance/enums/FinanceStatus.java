package com.finTrack.model.finance.enums;

public enum FinanceStatus {
    PAGA("Paga"),
    RECEBIDA("Recebida"),
    PENDENTE("Pendente");

    private final String descricao;

    FinanceStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}