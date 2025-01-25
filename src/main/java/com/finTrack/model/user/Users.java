package com.finTrack.model.user;

import com.finTrack.model.enums.UserRole;
import com.finTrack.model.finance.Finance;
import com.finTrack.model.finance.FinanceAccess;
import com.finTrack.model.finance.financialGoal.FinancialGoal;
import com.finTrack.model.finance.financialReport.FinancialReport;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;
    private String picture;
    private String authType;
    private UserRole role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Finance> finances = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<FinanceAccess> financeAccesses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FinancialGoal> financialGoals = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FinancialReport> financialReports = new ArrayList<>();


    public Users(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = UserRole.USER;
    }

    public Users() {
        this.role = UserRole.USER;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Finance> getFinances() {
        return finances;
    }

    public void setFinances(List<Finance> finances) {
        this.finances = finances;
    }

    public List<FinanceAccess> getFinanceAccesses() {
        return financeAccesses;
    }

    public void setFinanceAccesses(List<FinanceAccess> financeAccesses) {
        this.financeAccesses = financeAccesses;
    }

    public List<FinancialGoal> getFinancialGoals() {
        return financialGoals;
    }

    public void setFinancialGoals(List<FinancialGoal> financialGoals) {
        this.financialGoals = financialGoals;
    }

    public List<FinancialReport> getFinancialReports() {
        return financialReports;
    }

    public void setFinancialReports(List<FinancialReport> financialReports) {
        this.financialReports = financialReports;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
//        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
//        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return true;
//        return UserDetails.super.isEnabled();
    }
}