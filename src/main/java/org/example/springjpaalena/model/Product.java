package org.example.springjpaalena.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.springjpaalena.model.orders.OrderProduct;
import org.example.springjpaalena.model.review.Review;

import java.util.List;


@Getter
@Setter
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Value> valueList;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderProduct> orderProducts;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Review> reviewList;


}
