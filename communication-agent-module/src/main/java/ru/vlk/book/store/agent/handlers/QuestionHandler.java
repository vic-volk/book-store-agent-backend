package ru.vlk.book.store.agent.handlers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.agent.exception.AgentException;
import ru.vlk.book.store.agent.lingvistic.CategoryResolver;
import ru.vlk.book.store.agent.lingvistic.QuestionConverter;
import ru.vlk.book.store.agent.model.QuestionType;
import ru.vlk.book.store.agent.service.AgentMemoryService;
import ru.vlk.book.store.elastic.model.Book;
import ru.vlk.book.store.elastic.repository.BookRepository;

import javax.inject.Inject;

@Component
public class QuestionHandler implements Runnable {

    @Inject
    private AgentMemoryService agentMemoryService;

    @Inject
    private BookRepository bookRepository;

    @Inject
    @Qualifier("ontologyCategoryResolver")
    private CategoryResolver categoryResolver;

    @Inject
    @Qualifier("ontologyQuestionConverter")
    private QuestionConverter questionConverter;

    private String question;

    @Override
    public void run() {
        String question = this.question;
        //определить тип вопроса на основе вопросительных конструкций
        if (isQuestionPhrase(question)) {
            //извлечь название категории или книги
            QuestionType questionType = retrieveQuestionType(question);
            String query = questionConverter.questionToQuery(question);
            try {
                //произвести поиск
                Page<Book> results = searchByType(questionType, query);
                //выставить результаты
                agentMemoryService.setBookResults(results);
            } catch (AgentException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } else {
            // | если запрос не относится к необходимой категории, вывести фразу
            returnMisunderstandingPhrase();
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    private boolean isQuestionPhrase(String message) {
        return categoryResolver.isQuestion(message);
    }

    private QuestionType retrieveQuestionType(String message) {
        return categoryResolver.resolveQuestionType(message);
    }

    private Page<Book> searchByType(QuestionType type, String query) throws AgentException {
        switch (type) {
            case Concrete: return searchByConcrete(query);
            case Category: return searchByCategory(query);
            default: throw new AgentException("ERROR: Unrecognized question category.");
        }
    }

    private Page<Book> searchByConcrete(String questionTerm) {
        return bookRepository.findAll(new PageRequest(0, 10));
    }

    private Page<Book> searchByCategory(String questionTerm) {
        return bookRepository.findAll(new PageRequest(0, 10));
    }

    private String returnMisunderstandingPhrase() {
        return "Could you repeat question?";
    }
}
