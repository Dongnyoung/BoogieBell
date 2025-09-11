package com.noticeExtension.BoogieBell.domain;

import jakarta.persistence.*;   // JPA 어노테이션
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;     // 날짜 타입

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDate postedDate;
    private String url;

    @Enumerated(EnumType.STRING)
    private NoticeType type;

    public Notice(String title, LocalDate postedDate, String url, NoticeType type) {
        this.title = title;
        this.postedDate = postedDate;
        this.url = url;
        this.type = type;
    }

}
