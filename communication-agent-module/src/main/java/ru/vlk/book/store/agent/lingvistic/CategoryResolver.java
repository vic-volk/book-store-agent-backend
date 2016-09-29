package ru.vlk.book.store.agent.lingvistic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.agent.model.QuestionType;

@Component
public interface CategoryResolver {

    String resolveCategory(String question);

    QuestionType resolveQuestionType(String question);

    boolean isQuestion(String message);
}
