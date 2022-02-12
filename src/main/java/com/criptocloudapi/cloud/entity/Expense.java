package com.criptocloudapi.cloud.entity;

import com.criptocloudapi.cloud.model.ExpenseStatus;
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


@Table(name = "expense")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_expense")
    private UUID codExpense;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false, referencedColumnName = "cod_user")
    private User user;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "expense_value", nullable = false)
    private BigDecimal expenseValue;

    @Column(name = "dat_expense", nullable = false)
    private LocalDate datExpense;

    @Column(name = "status")
    private ExpenseStatus status;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
