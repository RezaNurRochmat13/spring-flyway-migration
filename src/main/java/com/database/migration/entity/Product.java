package com.database.migration.entity;

import com.database.migration.util.AuditModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "price")
    @NotBlank(message = "Price is mandatory")
    private String price;

    @Column(name = "qty")
    @NotBlank(message = "Qty is mandatory")
    private Integer qty;

    @Column(name = "category_id")
    @NotBlank(message = "Category id si mandatory")
    private Long categoryId;

    public Product(String name, String price, Integer qty, Long categoryId) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.categoryId = categoryId;
    }
}
