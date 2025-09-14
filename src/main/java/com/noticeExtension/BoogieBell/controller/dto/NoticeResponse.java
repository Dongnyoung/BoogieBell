package com.noticeExtension.BoogieBell.controller.dto;

import com.noticeExtension.BoogieBell.domain.Notice;
import com.noticeExtension.BoogieBell.domain.NoticeType;
import java.time.LocalDate;

public record NoticeResponse(
        Long id,
        String title,
        LocalDate postedDate,
        String url,
        NoticeType type
) {
    public static NoticeResponse from(Notice n) {
        return new NoticeResponse(
                n.getId(),
                n.getTitle(),
                n.getPostedDate(),
                n.getUrl(),
                n.getType()
        );
    }
}
