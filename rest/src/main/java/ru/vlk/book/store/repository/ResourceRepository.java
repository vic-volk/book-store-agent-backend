package ru.vlk.book.store.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ru.vlk.book.store.model.Resource;

@Repository
public interface ResourceRepository extends ElasticsearchRepository<Resource, String> {
}
