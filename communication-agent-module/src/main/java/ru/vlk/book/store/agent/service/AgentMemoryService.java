package ru.vlk.book.store.agent.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.elastic.model.Book;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class AgentMemoryService {

    private Page<Book> bookResults;

    @PostConstruct
    public void init() {
        bookResults = new PageImpl<>(new ArrayList<>());
    }

    public void setBookResults(Page<Book> bookResults) {
        this.bookResults = bookResults;
    }

    public Page<Book> getBookResults() {
        return this.bookResults;
    }
}
