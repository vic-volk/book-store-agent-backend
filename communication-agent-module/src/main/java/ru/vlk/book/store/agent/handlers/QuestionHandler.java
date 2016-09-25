package ru.vlk.book.store.agent.handlers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.agent.exception.AgentException;
import ru.vlk.book.store.agent.model.QuestionType;
import ru.vlk.book.store.agent.service.AgentMemoryService;
import ru.vlk.book.store.elastic.model.Book;
import ru.vlk.book.store.elastic.repository.BookRepository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Component
public class QuestionHandler implements Runnable {

    @Inject
    private AgentMemoryService agentMemoryService;

    @Inject
    private BookRepository bookRepository;

    private String question;

    @Override
    public void run() {
        String question = this.question;
        //определить тип вопроса на основе вопросительных конструкций
        if (isQuestionPhrase(question)) {
            //извлечь название категории или книги
            Map.Entry<QuestionType, String> questionType = retrieveQuestionType(question);
            try {
                //произвести поиск
                Page<Book> results = searchByType(questionType);
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

    private boolean isQuestionPhrase(String message) {
        return message.contains("do you have");
    }

    private Map.Entry<QuestionType, String> retrieveQuestionType(String message) {
        HashMap<QuestionType, String> map = new HashMap<>();
        map.put(QuestionType.Concrete, message);
        return map.entrySet().iterator().next();
    }

    private Page<Book> searchByType(Map.Entry<QuestionType, String> questionType) throws AgentException {
        switch (questionType.getKey()) {
            case Concrete: return searchByConcrete(questionType.getValue());
            case Category: return searchByCategory(questionType.getValue());
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
