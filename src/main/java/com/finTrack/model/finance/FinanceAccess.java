package com.finTrack.model.finance;

import com.finTrack.model.user.Users;
import jakarta.persistence.*;

@Entity
public class FinanceAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "finance_id", nullable = false)
    private Finance finance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    private String permission; // Exemplo: "READ", "WRITE"

    public FinanceAccess() {
    }

    public FinanceAccess(Finance finance, Users user, String permission) {
        this.finance = finance;
        this.user = user;
        this.permission = permission;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
