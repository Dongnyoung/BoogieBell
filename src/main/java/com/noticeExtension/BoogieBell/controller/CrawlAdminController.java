package com.noticeExtension.BoogieBell.controller;

import com.noticeExtension.BoogieBell.domain.NoticeType;
import com.noticeExtension.BoogieBell.service.CrawlUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class CrawlAdminController {

    private final CrawlUseCase crawl;

    // 예: POST /internal/crawl?type=POINT&topN=5
    @PostMapping("/crawl")
    public CrawlUseCase.CrawlResult crawl(@RequestParam NoticeType type,
                                          @RequestParam(defaultValue = "5") int topN) {
        return crawl.crawl(type, topN);
    }
}
