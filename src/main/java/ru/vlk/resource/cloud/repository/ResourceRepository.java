package ru.vlk.resource.cloud.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ru.vlk.resource.cloud.model.Resource;

@Repository
public interface ResourceRepository extends ElasticsearchRepository<Resource, String> {
}
