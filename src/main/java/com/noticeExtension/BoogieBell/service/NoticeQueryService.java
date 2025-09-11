package com.noticeExtension.BoogieBell.service;

import com.noticeExtension.BoogieBell.domain.Notice;
import com.noticeExtension.BoogieBell.domain.NoticeType;
import com.noticeExtension.BoogieBell.repo.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor   // final 필드를 자동으로 주입해주는 생성자 생성
@Transactional(readOnly = true)
public class NoticeQueryService {

    private final NoticeRepository repo;

    public List<Notice> getTop5(NoticeType type) {
        return repo.findTop5ByTypeOrderByPostedDateDesc(type);
    }
}
