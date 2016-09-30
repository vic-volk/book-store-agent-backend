package ru.vlk.book.store.agent.test.handlers;

import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class QuestionHandlerFactory {

    @Inject
    private QuestionHandler questionHandler;

    public QuestionHandler getQuestionHandler() {
        return this.questionHandler;
    }
}
