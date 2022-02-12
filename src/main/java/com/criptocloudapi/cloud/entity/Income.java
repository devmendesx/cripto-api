package com.criptocloudapi.cloud.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_income")
    private String codIncome;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false, referencedColumnName = "cod_user")
    private User user;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "income_value", nullable = false)
    private BigDecimal incomeValue;

    @Column(name = "dat_income", nullable = false)
    private LocalDate datIncome;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
