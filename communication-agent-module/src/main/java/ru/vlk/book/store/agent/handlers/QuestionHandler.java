package ru.vlk.book.store.agent.handlers;

import org.springframework.stereotype.Component;
import ru.vlk.book.store.agent.exception.AgentException;
import ru.vlk.book.store.agent.model.QuestionType;
import ru.vlk.book.store.agent.service.AgentMemoryService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Component
public class QuestionHandler implements Runnable {

    @Inject
    private AgentMemoryService agentMemoryService;

    private String question;

    public QuestionHandler() {
    }

    public QuestionHandler(String question) {
        this.question = question;
    }

    @Override
    public void run() {
        String question = this.question;
        //определить тип вопроса на основе вопросительных конструкций
        if (isQuestionPhrase(question)) {
            //извлечь название категории или книги
            Map.Entry<QuestionType, String> questionType = retrieveQuestionType(question);
            try {
                //произвести поиск
                searchByType(questionType);
                //выставить результаты
                agentMemoryService.setResults();
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

    private void searchByType(Map.Entry<QuestionType, String> questionType) throws AgentException {
        switch (questionType.getKey()) {
            case Concrete: searchByConcrete(questionType.getValue()); break;
            case Category: searchByCategory(questionType.getValue()); break;
            default: throw new AgentException("ERROR: Unrecognized question category.");
        }
    }

    private void searchByConcrete(String questionTerm) {

    }

    private void searchByCategory(String questionTerm) {

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
