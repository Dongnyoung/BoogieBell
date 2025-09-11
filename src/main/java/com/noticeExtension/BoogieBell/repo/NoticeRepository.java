package com.noticeExtension.BoogieBell.repo;

import com.noticeExtension.BoogieBell.domain.Notice;
import com.noticeExtension.BoogieBell.domain.NoticeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {


    // (선택) 최신 N개
    List<Notice> findTop5ByTypeOrderByPostedDateDesc(NoticeType type);
}
