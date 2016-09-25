package ru.vlk.book.store.agent.service;

import org.springframework.stereotype.Service;
import ru.vlk.book.store.agent.handlers.QuestionHandler;
import ru.vlk.book.store.agent.handlers.QuestionHandlerFactory;
import ru.vlk.book.store.agent.model.Question;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@Service
public class AgentService {

    @Inject
    private QuestionHandlerFactory questionHandlerFactory;

    private Question currentQuestion;
    private Executor executor = new ForkJoinPool();

    @PostConstruct
    public void init() {
        currentQuestion = new Question("");
    }

    public void handleQuestion(String message) {
        currentQuestion.setMessage(message);
        executor.execute(prepareQuestionHandler(currentQuestion));
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    private QuestionHandler prepareQuestionHandler(Question question) {
        QuestionHandler questionHandler = questionHandlerFactory.getQuestionHandler();
        questionHandler.setQuestion(currentQuestion.getMessage());
        return questionHandler;
    }
}
