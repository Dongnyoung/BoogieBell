package com.noticeExtension.BoogieBell.controller;

import com.noticeExtension.BoogieBell.controller.dto.NoticeResponse;
import com.noticeExtension.BoogieBell.domain.Notice;
import com.noticeExtension.BoogieBell.domain.NoticeType;
import com.noticeExtension.BoogieBell.service.NoticeQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class NoticeApiControllerTest {

    @Mock
    NoticeQueryService service;

    @InjectMocks
    NoticeApiController controller;

    @Test
    void top5_ok() {
        // given
        given(service.getTop5(NoticeType.SCHOOL)).willReturn(
                List.of(
                        new Notice("제목B", LocalDate.of(2025, 9, 6), "u2", NoticeType.SCHOOL),
                        new Notice("제목A", LocalDate.of(2025, 9, 5), "u1", NoticeType.SCHOOL)
                )
        );

        // when
        List<NoticeResponse> result = controller.top5(NoticeType.SCHOOL);

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).title()).isEqualTo("제목B");  // record는 .title()
        assertThat(result.get(1).url()).isEqualTo("u1");      // record는 .url()
    }
}
