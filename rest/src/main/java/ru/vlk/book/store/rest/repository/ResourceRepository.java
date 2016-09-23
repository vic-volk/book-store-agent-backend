package ru.vlk.book.store.rest.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ru.vlk.book.store.rest.model.Resource;

@Repository
public interface ResourceRepository extends ElasticsearchRepository<Resource, String> {
}
