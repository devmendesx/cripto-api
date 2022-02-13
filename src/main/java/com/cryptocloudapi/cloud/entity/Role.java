package com.cryptocloudapi.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Table(name = "roles") @Entity
@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
