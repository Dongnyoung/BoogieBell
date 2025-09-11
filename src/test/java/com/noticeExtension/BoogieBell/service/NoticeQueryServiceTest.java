package com.noticeExtension.BoogieBell.service;

import com.noticeExtension.BoogieBell.domain.Notice;
import com.noticeExtension.BoogieBell.domain.NoticeType;
import com.noticeExtension.BoogieBell.repo.NoticeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(NoticeQueryService.class) // 서비스 빈만 추가 주입
class NoticeQueryServiceTest {

    @Autowired NoticeRepository repo;
    @Autowired NoticeQueryService service;

    @Test
    @DisplayName("서비스: 타입별 최신 5개 조회")
    void getTop5() {
        // given
        repo.save(new Notice("A", LocalDate.of(2025,9,1), "u1", NoticeType.POINT));
        repo.save(new Notice("B", LocalDate.of(2025,9,3), "u2", NoticeType.POINT));
        repo.save(new Notice("C", LocalDate.of(2025,9,2), "u3", NoticeType.POINT));
        repo.save(new Notice("D", LocalDate.of(2025,9,5), "u4", NoticeType.POINT));
        repo.save(new Notice("E", LocalDate.of(2025,9,7), "u5", NoticeType.POINT));
        repo.save(new Notice("F", LocalDate.of(2025,9,6), "u6", NoticeType.POINT));

        // when
        List<Notice> result = service.getTop5(NoticeType.POINT);

        // then
        assertThat(result).hasSize(5);
        assertThat(result).extracting(Notice::getTitle)
                .containsExactly("E","F","D","B","C"); // 9/7, 9/6, 9/5, 9/3, 9/2
    }
}
