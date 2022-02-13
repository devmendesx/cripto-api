package com.cryptocloudapi.cloud.app.config.security.service.dto;


import lombok.Data;

@Data
public class TokenDto {
    private final String token;
    private final String type;

    public TokenDto(String token, String type) {
        this.token = token;
        this.type = type;
    }
}
