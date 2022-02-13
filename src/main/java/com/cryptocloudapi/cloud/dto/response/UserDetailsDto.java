package com.cryptocloudapi.cloud.dto.response;

import com.cryptocloudapi.cloud.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class UserDetailsDto {
    private String cod;
    private String email;
    private String firstName;
    private String lastName;
    private List<Role> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
