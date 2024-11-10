package com.skt5cean.cheerup.company.domain;

import com.skt5cean.cheerup.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Interview extends Document {

//    public enum StateType {
//        COMPLETED, PROCESSING, FAILED
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;


//    @Enumerated(EnumType.STRING)
//    @Column(name = "state", nullable = false)
//    private StateType state;

    public static Interview createInterview(Company company, String title, String contents) {
        Interview interview = new Interview();
        interview.company = company;
        interview.title = title;
        interview.contents = contents;
        return interview;
    }
}
