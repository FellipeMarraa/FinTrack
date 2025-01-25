package com.finTrack.model.finance.financialGoal;

import com.finTrack.model.finance.financialGoal.FinancialGoal;
import com.finTrack.model.user.Users;
import jakarta.persistence.*;

@Entity
public class GoalAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = false)
    private FinancialGoal goal;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    private String permission; // "VIEW", "EDIT"

    public GoalAccess() {}

    public GoalAccess(FinancialGoal goal, Users user, String permission) {
        this.goal = goal;
        this.user = user;
        this.permission = permission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FinancialGoal getGoal() {
        return goal;
    }

    public void setGoal(FinancialGoal goal) {
        this.goal = goal;
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