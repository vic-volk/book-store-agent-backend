package ru.vlk.book.store.rest.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.elastic.model.Book;
import ru.vlk.book.store.elastic.repository.BookRepository;

import javax.inject.Inject;

@Component
public class BookService {

    @Inject
    private BookRepository bookRepository;

    public Page<Book> listAll() {
        return bookRepository.findAll(new PageRequest(0, 10));
    }

    public Book indexSampleBook() {
        Book book = new Book();
        book.setName("Hobbit");
        book.setAuthor("J.R.R.Tolkien");
        return bookRepository.save(book);
    }
}
