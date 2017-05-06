package ru.vlk.book.store.elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;
import ru.vlk.book.store.elastic.model.Notice;

@Repository
public interface NoticeRepository extends ElasticsearchCrudRepository<Notice, String> {
}
