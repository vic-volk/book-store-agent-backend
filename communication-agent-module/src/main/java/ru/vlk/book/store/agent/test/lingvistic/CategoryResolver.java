package ru.vlk.book.store.agent.test.lingvistic;

import org.springframework.stereotype.Component;
import ru.vlk.book.store.agent.test.model.QuestionType;

@Component
public interface CategoryResolver {

    String resolveCategory(String question);

    QuestionType resolveQuestionType(String question);

    boolean isQuestion(String message);
}
