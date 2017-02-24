package ru.vlk.book.store.agent.lingvistic;

import org.springframework.stereotype.Component;
import ru.vlk.book.store.agent.model.QuestionType;
import ru.vlk.book.store.agent.service.AgentMemoryService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class OntologyCategoryResolver implements CategoryResolver {

    @Inject
    private AgentMemoryService agentMemoryService;

    private List<String> templates;

    public OntologyCategoryResolver() {
        this.templates = new ArrayList<>();
    }

    @Override
    public String resolveCategory(String question) {
        return null;
    }

    @Override
    public QuestionType resolveQuestionType(String question) {
        Set<String> categoryQuestionWords = agentMemoryService.getCategoryQuestionWords();
        return categoryQuestionWords.stream().anyMatch(question::contains)
                ? QuestionType.Category : QuestionType.Concrete;
    }

    @Override
    public boolean isQuestion(String message) {
        Set<String> patterns = agentMemoryService.getQuestionPatterns();
        return patterns.stream().anyMatch(message::contains);
    }
}
