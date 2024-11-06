package com.skt5cean.cheerup.company.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    private String link;

    public static Bookmark createBookmark(Company company, String link){
        Bookmark bookmark = new Bookmark();
        bookmark.company = company;
        bookmark.link = link;
        return bookmark;
    }

    public void update(String link){
        this.link = link;
    }
}
