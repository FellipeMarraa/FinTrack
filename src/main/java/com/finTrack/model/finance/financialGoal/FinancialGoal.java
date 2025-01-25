package com.finTrack.model.finance.financialGoal;

import com.finTrack.model.user.Users;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class FinancialGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;  // Descrição da meta
    private BigDecimal valorMeta;  // Valor total da meta
    private BigDecimal valorAtual;  // Valor acumulado até o momento
    private BigDecimal saldoNecessario;  // O valor faltante para cumprir a meta
    private Integer mesLimite;  // Mês até quando a meta precisa ser alcançada
    private Integer anoLimite;  // Ano até quando a meta precisa ser alcançada

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToMany
    private List<SubGoal> submetas;  // Lista de submetas

    public FinancialGoal() {}

    // Getters e Setters

    public BigDecimal getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(BigDecimal valorMeta) {
        this.valorMeta = valorMeta;
    }

    public BigDecimal getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(BigDecimal valorAtual) {
        this.valorAtual = valorAtual;
    }

    public BigDecimal getSaldoNecessario() {
        return saldoNecessario;
    }

    public void setSaldoNecessario(BigDecimal saldoNecessario) {
        this.saldoNecessario = saldoNecessario;
    }

    public List<SubGoal> getSubmetas() {
        return submetas;
    }

    public void setSubmetas(List<SubGoal> submetas) {
        this.submetas = submetas;
    }

    public Integer getMesLimite() {
        return mesLimite;
    }

    public void setMesLimite(Integer mesLimite) {
        this.mesLimite = mesLimite;
    }

    public Integer getAnoLimite() {
        return anoLimite;
    }

    public void setAnoLimite(Integer anoLimite) {
        this.anoLimite = anoLimite;
    }

    public void calcularValorMeta() {
        BigDecimal totalSubmetas = BigDecimal.ZERO;
        for (SubGoal subGoal : submetas) {
            totalSubmetas = totalSubmetas.add(subGoal.getValor());
        }
        this.valorMeta = totalSubmetas;
    }
}
