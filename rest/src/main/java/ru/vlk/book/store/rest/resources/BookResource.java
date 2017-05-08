package ru.vlk.book.store.rest.resources;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.elastic.model.Book;
import ru.vlk.book.store.rest.service.BookService;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/book")
@Component
public class BookResource {

    @Inject
    private BookService bookService;

    @GET
    @Produces("application/json")
    public List<Book> getBooks(@QueryParam("q") String queryTerm) {
        if(StringUtils.isBlank(queryTerm)) {
            queryTerm = "*:*";
        }
        return bookService.search(queryTerm).getContent();
    }

    @POST
    public void indexSample() {
        bookService.indexHobbit();
        bookService.indexTheCognitiveEnterprise();
        bookService.indexTheLordOfTheRings();
    }
}
