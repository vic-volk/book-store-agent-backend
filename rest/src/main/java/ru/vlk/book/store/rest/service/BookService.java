package ru.vlk.book.store.rest.service;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
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

    public Page<Book> search(String query) {
        return bookRepository.search(new QueryStringQueryBuilder(query), new PageRequest(0, 10));
    }

    public Book indexHobbit() {
        Book book = new Book();
        book.setName("The Hobbit");
        book.setAuthor("J.R.R.Tolkien");
        book.setCover("http://localhost/theHobbit.jpg");
        book.setDescription("Bilbo Baggins is a hobbit who enjoys a comfortable, unambitious life, " +
                "rarely traveling any farther than his pantry or cellar. " +
                "But his contentment is disturbed when the wizard Gandalf and a company " +
                "of dwarves arrive on his doorstep one day to whisk him away on an " +
                "adventure. They have launched a plot to raid the treasure hoard guarded " +
                "by Smaug the Magnificent, a large and very dangerous dragon. " +
                "Bilbo reluctantly joins their quest, unaware that on his journey to the " +
                "Lonely Mountain he will encounter both a magic ring and a frightening " +
                "creature known as Gollum.");
        return bookRepository.save(book);
    }

    public Book indexTheLordOfTheRings() {
        Book book = new Book();
        book.setName("The Lord Of The Rings");
        book.setAuthor("J.R.R.Tolkien");
        book.setCover("http://localhost/theLordOfTheRings.jpg");
        book.setDescription("In ancient times the Rings of Power were crafted by the Elven-smiths, " +
                        "and Sauron, the Dark Lord, forged the One Ring, filling it with his " +
                        "own power so that he could rule all others. But the One Ring was taken " +
                        "from him, and though he sought it throughout Middle-earth, it remained " +
                        "lost to him. After many ages it fell by chance into the hands of the " +
                        "hobbit Bilbo Baggins.");
        return bookRepository.save(book);
    }

    public Book indexTheCognitiveEnterprise() {
        Book book = new Book();
        book.setName("The Cognitive Enterprise");
        book.setAuthor("Bob Lewis");
        book.setCover("http://localhost/cognitive.jpg");
        book.setDescription("" +
                "The PROCESS, Technology, people model was designed " +
                "for the industrial age and command-and-control management. " +
                "This book introduces its 21st century replacement - customers, " +
                "communities and capabilities - and explains how this new model " +
                "works in a connect-and-collaborate world. What is a cognitive " +
                "enterprise? It's a business that's smarter than the smartest " +
                "people running it. The organization knows what its workforce " +
                "knows; that is, knowledge is shared widely. It's a business " +
                "that acts with purpose rather than being a space in which ");
        return bookRepository.save(book);
    }
}
