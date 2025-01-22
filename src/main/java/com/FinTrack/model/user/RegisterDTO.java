package com.FinTrack.model.user;

import com.FinTrack.model.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}
