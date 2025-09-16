package com.noticeExtension.BoogieBell.service;

import com.noticeExtension.BoogieBell.crawl.CrawlerRegistry;
import com.noticeExtension.BoogieBell.crawl.NoticeCrawler;
import com.noticeExtension.BoogieBell.domain.Notice;
import com.noticeExtension.BoogieBell.domain.NoticeType;
import com.noticeExtension.BoogieBell.repo.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CrawlUseCase {

    private final CrawlerRegistry registry;
    private final NoticeRepository repo;

    @Transactional
    public CrawlResult crawl(NoticeType type, int topN) {
        int saved = 0, skipped = 0;
        String error = null;

        try {
            NoticeCrawler crawler = registry.get(type);
            if (crawler == null) throw new IllegalArgumentException("No crawler for type: " + type);

            for (var item : crawler.fetchTopN(topN)) {
                // 중복 방지: url + postedDate
                if (repo.existsByUrlAndPostedDate(item.url(), item.postedDate())) {
                    skipped++;
                    continue;
                }
                repo.save(new Notice(item.title(), item.postedDate(), item.url(), type));
                saved++;
            }
        } catch (Exception e) {
            error = e.getMessage();
        }
        return new CrawlResult(type, saved, skipped, error);
    }

    @Transactional
    public CrawlResult crawlAll(int topN) {
        int saved = 0, skipped = 0;
        StringBuilder sb = new StringBuilder();
        for (var t : NoticeType.values()) {
            CrawlResult r = crawl(t, topN);
            saved += r.saved(); skipped += r.skipped();
            if (r.error() != null) sb.append(t).append(": ").append(r.error()).append("; ");
        }
        return new CrawlResult(null, saved, skipped, sb.length()==0? null : sb.toString());
    }

    public record CrawlResult(NoticeType type, int saved, int skipped, String error) {}
}
