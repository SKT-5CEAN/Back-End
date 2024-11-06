package com.skt5cean.cheerup.user.domain;

import com.skt5cean.cheerup.company.domain.Document;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class TotalDocument extends Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "total_document_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public static TotalDocument createTotalDocument(User user, String title, String contents){
        TotalDocument totalDocument = new TotalDocument();
        totalDocument.user = user;
        totalDocument.title = title;
        totalDocument.contents = contents;
        return totalDocument;
    }

    public void update(String contents){
        this.contents = contents;
    }
}
