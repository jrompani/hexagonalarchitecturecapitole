package com.app.hexarchitecture.capitole.infrastructure.adapters.out.persistence.price.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class EntityProduct {
    @Id
    private Long id;

    @Column
    private String name;
}
