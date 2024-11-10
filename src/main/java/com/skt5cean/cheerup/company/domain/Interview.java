package com.skt5cean.cheerup.company.domain;

import com.skt5cean.cheerup.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Interview extends Document {

    public enum StateType {
        COMPLETED, PROCESSING, FAILED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;


    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private StateType state;

    // 추후 state 관련해서 비동기 방식으로 구현 예정.
    // 답변 반환 받은 후 interview 생성 예정이라서 state가 completed로 초기화됨.
    public static Interview createInterview(Company company, String title, String contents) {
        Interview interview = new Interview();
        interview.company = company;
        interview.title = title;
        interview.contents = contents;
        interview.state = StateType.COMPLETED;
        return interview;
    }
}
