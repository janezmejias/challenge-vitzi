package com.lean.vitzi.infrastructure.adapters.out.mysql.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

/**
 * @author janez@vitzi.com
 * @version 2022-10-11
 */
@Entity
@Table(name = "product")
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "created_at")
    @NotNull
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @NotNull
    private Timestamp updatedAt;

    @PrePersist
    private void prePersistStatus() {
        if (Objects.isNull(createdAt)) {
            createdAt = Timestamp.from(Instant.now());
        }
        if (Objects.isNull(updatedAt)) {
            updatedAt = Timestamp.from(Instant.now());
        }
        if (Objects.isNull(isEnabled)) {
            isEnabled = Boolean.TRUE;
        }
    }

}
