package com.noticeExtension.BoogieBell.controller;

import com.noticeExtension.BoogieBell.controller.dto.NoticeResponse;
import com.noticeExtension.BoogieBell.domain.NoticeType;
import com.noticeExtension.BoogieBell.service.NoticeQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeApiController {

    private final NoticeQueryService service;

    public NoticeApiController(NoticeQueryService service) {
        this.service = service;
    }

    // GET /api/notices/top5?type=SCHOOL
    @GetMapping("/top5")
    public List<NoticeResponse> top5(@RequestParam NoticeType type) {
        return service.getTop5(type).stream()
                .map(NoticeResponse::from)
                .toList();
    }
}
