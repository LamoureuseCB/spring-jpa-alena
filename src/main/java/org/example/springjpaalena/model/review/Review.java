package org.example.springjpaalena.model.review;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.springjpaalena.model.Product;
import org.example.springjpaalena.model.user.User;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@ToString
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated

    private PublishStatus published;
    private int rate;
    @Column(name = "review_text")
    private String reviewText;
    @Column(name = "publish_date")
    private LocalDate localDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
