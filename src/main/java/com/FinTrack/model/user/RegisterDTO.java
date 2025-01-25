package com.FinTrack.model.user;

import com.FinTrack.model.enums.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {

}
