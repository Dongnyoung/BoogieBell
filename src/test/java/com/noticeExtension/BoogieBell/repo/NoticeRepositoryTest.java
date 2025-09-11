package com.noticeExtension.BoogieBell.repo;

import com.noticeExtension.BoogieBell.domain.Notice;
import com.noticeExtension.BoogieBell.domain.NoticeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class NoticeRepositoryTest {

    @Autowired
    NoticeRepository repo;

    @Test
    @DisplayName("타입별 최신 5개를 postedDate DESC로 반환한다")
    void findTop5ByTypeOrderByPostedDateDesc() {
        // given: SCHOOL 7건 + STUDENT 2건
        for (int i = 1; i <= 6; i++) {
            repo.save(new Notice(
                    "공지" + i,
                    LocalDate.of(2025, 9, i),     // 날짜: 9월1일 ~ 9월6일
                    "url" + i,
                    NoticeType.SCHOOL
            ));
        }

        // when
        List<Notice> topFive = repo.findTop5ByTypeOrderByPostedDateDesc(NoticeType.SCHOOL);

        // then
        assertThat(topFive).hasSize(5);
        assertThat(topFive).extracting(Notice::getTitle)
                .containsExactly("공지6", "공지5", "공지4", "공지3", "공지2"); // 최신 5개
    }
}
