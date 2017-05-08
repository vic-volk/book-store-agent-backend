package ru.vlk.book.store.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.vlk.book.store.elastic.model.Book;
import ru.vlk.book.store.elastic.repository.BookRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestElasticConfig.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testGetBooks() {
        Iterable<Book> books = bookRepository.findAll();
    }
}
