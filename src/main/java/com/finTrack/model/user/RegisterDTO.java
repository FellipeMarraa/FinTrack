package com.finTrack.model.user;

import com.finTrack.model.enums.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {

}
