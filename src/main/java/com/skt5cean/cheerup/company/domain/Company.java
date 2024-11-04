package com.skt5cean.cheerup.company.domain;

import com.skt5cean.cheerup.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // 자소서의 질문 - 답변
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Resume> resumes = new ArrayList<>();

    // 관련 링크
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Bookmark> bookmarks = new ArrayList<>();

    // 해당 기업의 전형
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Step> steps = new ArrayList<>();

    // 회사명
    @Column(nullable = false)
    private String companyName;

    // 인재상
    private String talent;

    // 기업 제품
    private String product;

    // 최신 뉴스
    private String news;

    // 기타
    private String other;

    public static Company createCompany(String companyName, List<Step> steps, User user){
        Company company = new Company();
        company.companyName = companyName;
        company.steps = steps;
        company.user = user;
        return company;
    }

    public void updateCompanyInfo(String talent, String product, String news, String other){
        this.talent = talent;
        this.product = product;
        this.news = news;
        this.other = other;
    }
}
