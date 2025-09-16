// com.noticeExtension.BoogieBell.crawl.NoticeCrawler
package com.noticeExtension.BoogieBell.crawl;

import com.noticeExtension.BoogieBell.domain.NoticeType;
import java.time.LocalDate;
import java.util.List;

public interface NoticeCrawler {
    NoticeType supports();
    List<CrawledItem> fetchTopN(int n) throws Exception;

    record CrawledItem(String title, LocalDate postedDate, String url) {}
}
