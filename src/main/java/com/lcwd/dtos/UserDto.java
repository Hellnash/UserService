package com.lcwd.dtos;

import java.util.UUID;

public class UserDto {
    private UUID userId;
    private String name;
    private String email;
    private String about;

    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
