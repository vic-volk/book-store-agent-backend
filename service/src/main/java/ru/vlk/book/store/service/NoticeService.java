package ru.vlk.book.store.service;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.elastic.model.Notice;
import ru.vlk.book.store.elastic.repository.NoticeRepository;

@Component
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public Notice indexNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public Page<Notice> listAll(String queryTerm) {
        return noticeRepository.search(new QueryStringQueryBuilder(queryTerm), new PageRequest(0, 10));
    }
}
