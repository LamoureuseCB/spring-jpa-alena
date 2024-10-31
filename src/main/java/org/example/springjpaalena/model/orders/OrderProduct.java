package org.example.springjpaalena.model.orders;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.springjpaalena.model.Product;

@Getter
@Setter
@Entity
@Table(name = "orders_products")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;



}
