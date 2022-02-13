package com.cryptocloudapi.cloud.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UserPasswordChangeDto {
    private String code;
    private String oldPassword;
    private String newPassword;
}
