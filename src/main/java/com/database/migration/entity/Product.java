package com.database.migration.entity;

import com.database.migration.util.AuditModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "category_id")
    private Long categoryId;

    public Product(String name, String price, Integer qty) {
        this.name = name;
        this.price = price;
        this.qty = qty;
    }
}
