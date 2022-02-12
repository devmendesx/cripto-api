package com.criptocloudapi.cloud.entity;

import com.criptocloudapi.cloud.utils.ChargeStatus;
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


@Table(name = "charge")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_charge")
    private String codDisbursement;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false, referencedColumnName = "cod_user")
    private User user;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "charge_value", nullable = false)
    private BigDecimal chargeValue;

    @Column(name = "dat_charge", nullable = false)
    private LocalDate datDisbursement;

    @Column(name = "status")
    private ChargeStatus status;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
