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
import java.util.UUID;


@Table(name = "revenue")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_revenue")
    private UUID codRevenue;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false, referencedColumnName = "cod_user")
    private User user;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "revenue_value", nullable = false)
    private BigDecimal revenueValue;

    @Column(name = "dat_revenue", nullable = false)
    private LocalDate datRevenue;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
