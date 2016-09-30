package ru.vlk.book.store.agent.test.lingvistic;

import org.springframework.stereotype.Component;
import ru.vlk.book.store.agent.test.service.AgentMemoryService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Component
public class OntologyQuestionConverter implements QuestionConverter {

    @Inject
    private AgentMemoryService agentMemoryService;

    @PostConstruct
    private void init() {
    }

    @Override
    public String questionToQuery(String question) {
        return null;
    }
}
