package ru.vlk.book.store.agent.lingvistic;

import org.springframework.stereotype.Component;

@Component
public interface QuestionConverter {

    String questionToQuery(String question);
}
