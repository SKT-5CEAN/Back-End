package com.skt5cean.cheerup.user.domain;

import com.skt5cean.cheerup.company.domain.Document;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class TotalInterview extends Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "total_interview_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public static TotalInterview createTotalInterview(User user, String title, String contents){
        TotalInterview totalInterview = new TotalInterview();
        totalInterview.user = user;
        totalInterview.title = title;
        totalInterview.contents = contents;
        return totalInterview;
    }

    public void update(String contents){
        this.contents = contents;
    }
}