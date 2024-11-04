package com.skt5cean.cheerup.company.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Recap extends Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Step step;

    public static Recap createRecap(Step step, String title, String contents){
        Recap recap = new Recap();
        recap.step = step;
        recap.title = title;
        recap.contents = contents;
        return recap;
    }
}
