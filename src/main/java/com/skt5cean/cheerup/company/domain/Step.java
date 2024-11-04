package com.skt5cean.cheerup.company.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "step_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @OneToMany(mappedBy = "step", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "step", cascade = CascadeType.REMOVE)
    private List<Recap> recaps = new ArrayList<>();

    // 전형명(서류전형, 기술면접 등)
    private String stepName;

    // 전형 탈락 여부
    private Boolean isFailed;

    public static Step createStep(Company company, String stepName){
        Step step = new Step();
        step.company = company;
        step.stepName = stepName;
        step.isFailed = false;
        return step;
    }
}
