package com.finTrack.model.finance;

import com.finTrack.model.user.Users;
import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "shared_permission_id", nullable = true)
    private FinanceAccess financeAccess; // Permissão vinculada

    public Category() {}

    public Category(String name, Users user) {
        this.name = name;
        this.user = user;
    }

    // Getters e setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public FinanceAccess getFinanceAccess() {
        return financeAccess;
    }

    public void setFinanceAccess(FinanceAccess financeAccess) {
        this.financeAccess = financeAccess;
    }
}
