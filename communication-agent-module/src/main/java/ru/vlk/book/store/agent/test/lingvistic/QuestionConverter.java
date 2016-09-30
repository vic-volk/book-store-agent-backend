package ru.vlk.book.store.agent.test.lingvistic;

import org.springframework.stereotype.Component;

@Component
public interface QuestionConverter {

    String questionToQuery(String question);
}
