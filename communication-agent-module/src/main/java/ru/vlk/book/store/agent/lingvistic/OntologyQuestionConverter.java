package ru.vlk.book.store.agent.lingvistic;

import org.springframework.stereotype.Component;
import ru.vlk.book.store.agent.service.AgentMemoryService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OntologyQuestionConverter implements QuestionConverter {

    @Inject
    private AgentMemoryService agentMemoryService;

    @PostConstruct
    private void init() {
    }

    @Override
    public String questionToQuery(String question) {
        String questionPattern = retrieveQuestionPattern(question);
        Set<String> categoryWords = retrieveCategoryWords(question);
        String bookName = cutExtraWords(question, questionPattern, categoryWords);
        return wrapWithElasticQuerySyntax(bookName);
    }

    private String retrieveQuestionPattern(String question) {
        Set<String> questionPatterns = agentMemoryService.getQuestionPatterns();
        return questionPatterns.stream().filter(question::contains).collect(Collectors.joining());
    }

    private Set<String> retrieveCategoryWords(String question) {
        return agentMemoryService.getCategoryQuestionWords();
    }

    private String cutExtraWords(String question, String questionPhrase, Set<String> otherWords) {
        question = question.replaceFirst(questionPhrase, "");
        for (String otherWord : otherWords) {
            question = question.replace(otherWord, "");
        }
        return question;
    }

    private String wrapWithElasticQuerySyntax(String bookName) {
        return "name:\"" + bookName + "\"";
    }
}
