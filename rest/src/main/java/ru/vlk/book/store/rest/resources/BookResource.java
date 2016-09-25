package ru.vlk.book.store.rest.resources;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.elastic.model.Book;
import ru.vlk.book.store.rest.service.BookService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/book")
@Component
public class BookResource {

    @Inject
    private BookService bookService;

    @GET
    @Produces("application/json")
    public Page<Book> getBooks() {
        return bookService.listAll();
    }

    @POST
    public void indexSample() {
        bookService.indexSampleBook();
    }
}
