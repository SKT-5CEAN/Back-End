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

}
