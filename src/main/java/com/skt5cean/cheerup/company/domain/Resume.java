package com.skt5cean.cheerup.company.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Resume extends Document{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    public static Resume createResume(Company company, String title){
        Resume resume = new Resume();
        resume.company = company;
        resume.title = title;
        return resume;
    }

    public void update(String title, String contents){
        this.title = title;
        this.contents = contents;
    }
}
