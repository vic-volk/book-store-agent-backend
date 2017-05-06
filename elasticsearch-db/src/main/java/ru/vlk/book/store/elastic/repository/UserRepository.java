package ru.vlk.book.store.elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ru.vlk.book.store.elastic.model.User;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {
}
