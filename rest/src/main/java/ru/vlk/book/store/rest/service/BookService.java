package ru.vlk.book.store.rest.service;

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

    public Book indexHobbit() {
        Book book = new Book();
        book.setName("The Hobbit");
        book.setAuthor("J.R.R.Tolkien");
        book.setCover("http://localhost/MV5BMTcwNTE4MTUxMl5BMl5BanBnXkFtZTcwMDIyODM4OA@@._V1_UY1200_CR90,0,630,1200_AL_.jpg");
        book.setCover("http://localhost/OneVolumeLOTR-Holland-port.jpg");
        return bookRepository.save(book);
    }

    public Book indexTheLordOfTheRings() {
        Book book = new Book();
        book.setName("The Lord Of The Rings");
        book.setAuthor("J.R.R.Tolkien");
        book.setCover("http://localhost/OneVolumeLOTR-Holland-port.jpg");
        return bookRepository.save(book);
    }

    public Book indexTheCognitiveEnterprise() {
        Book book = new Book();
        book.setName("The Cognitive Enterprise");
        book.setAuthor("Bob Lewis");
        book.setCover("http://localhost/51VEO88+2BL._SX331_BO1,204,203,200_.jpg");
        return bookRepository.save(book);
    }
}
