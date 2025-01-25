package com.finTrack.model.finance;

import com.finTrack.model.finance.enums.FinanceCategoria;
import com.finTrack.model.finance.enums.FinanceRecorrencia;
import com.finTrack.model.finance.enums.FinanceStatus;
import com.finTrack.model.finance.enums.FinanceTipo;
import com.finTrack.model.user.Users;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String descricao;
    private BigDecimal valor;
    private BigDecimal mes;
    private BigDecimal ano;
    @Enumerated(EnumType.STRING)
    private FinanceCategoria categoria;
    @Enumerated(EnumType.STRING)
    private FinanceTipo tipo;
    @Enumerated(EnumType.STRING)
    private FinanceRecorrencia recorrencia;
    @Enumerated(EnumType.STRING)
    private FinanceStatus status;
    private Integer numParcelas;
    private Integer parcelaAtual;
    private String referencia;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Users owner;

    @OneToMany(mappedBy = "finance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FinanceAccess> sharedWith = new ArrayList<>();

    public Finance() {
    }

    public Finance(String id, String descricao, BigDecimal valor, BigDecimal mes, BigDecimal ano,
                   FinanceCategoria categoria, FinanceTipo tipo, FinanceRecorrencia recorrencia,
                   FinanceStatus status, Integer numParcelas, Users owner) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.mes = mes;
        this.ano = ano;
        this.categoria = categoria;
        this.tipo = tipo;
        this.recorrencia = recorrencia;
        this.status = status;
        this.numParcelas = numParcelas;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getMes() {
        return mes;
    }

    public void setMes(BigDecimal mes) {
        this.mes = mes;
    }

    public BigDecimal getAno() {
        return ano;
    }

    public void setAno(BigDecimal ano) {
        this.ano = ano;
    }

    public FinanceCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(FinanceCategoria categoria) {
        this.categoria = categoria;
    }

    public FinanceTipo getTipo() {
        return tipo;
    }

    public void setTipo(FinanceTipo tipo) {
        this.tipo = tipo;
    }

    public FinanceRecorrencia getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(FinanceRecorrencia recorrencia) {
        this.recorrencia = recorrencia;
    }

    public FinanceStatus getStatus() {
        return status;
    }

    public void setStatus(FinanceStatus status) {
        this.status = status;
    }

    public Integer getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(Integer numParcelas) {
        this.numParcelas = numParcelas;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public List<FinanceAccess> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(List<FinanceAccess> sharedWith) {
        this.sharedWith = sharedWith;
    }

    public Integer getParcelaAtual() {
        return parcelaAtual;
    }

    public void setParcelaAtual(Integer parcelaAtual) {
        this.parcelaAtual = parcelaAtual;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}