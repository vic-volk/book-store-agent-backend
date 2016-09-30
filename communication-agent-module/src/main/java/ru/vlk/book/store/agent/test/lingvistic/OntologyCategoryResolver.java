package ru.vlk.book.store.agent.test.lingvistic;

import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.agent.test.model.QuestionType;
import ru.vlk.book.store.agent.test.service.AgentMemoryService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
        return null;
    }

    @Override
    public boolean isQuestion(String message) {
        OWLOntology ontology = agentMemoryService.getOntology();
        Stream<OWLEntity> entities = ontology.signature();
        entities.forEach(e ->
            EntitySearcher.getAnnotations(e, ontology).forEach(a ->
                    System.out.println(a.getValue().asLiteral().get().getLiteral())));
        return false;
    }
}
