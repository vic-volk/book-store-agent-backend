package ru.vlk.book.store.elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ru.vlk.book.store.elastic.model.Book;

@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {
}
