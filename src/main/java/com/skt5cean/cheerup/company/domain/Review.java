package com.skt5cean.cheerup.company.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Review extends Document{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Step step;

    public static Review createReview(Step step, String contents){
        Review review = new Review();
        review.step = step;
        review.contents = contents;
        return review;
    }
}
