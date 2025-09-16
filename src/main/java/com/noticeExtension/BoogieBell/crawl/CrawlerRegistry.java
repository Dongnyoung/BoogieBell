// com.noticeExtension.BoogieBell.crawl.CrawlerRegistry
package com.noticeExtension.BoogieBell.crawl;

import com.noticeExtension.BoogieBell.domain.NoticeType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CrawlerRegistry {
    private final Map<NoticeType, NoticeCrawler> map;
    public CrawlerRegistry(List<NoticeCrawler> crawlers) {
        this.map = crawlers.stream().collect(Collectors.toMap(NoticeCrawler::supports, c -> c));
    }
    public NoticeCrawler get(NoticeType type) { return map.get(type); }
}
