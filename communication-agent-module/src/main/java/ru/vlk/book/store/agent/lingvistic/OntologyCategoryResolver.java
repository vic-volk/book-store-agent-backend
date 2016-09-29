package ru.vlk.book.store.agent.lingvistic;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.OWLOntologyWalker;
import org.semanticweb.owlapi.util.OWLOntologyWalkerVisitor;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.agent.model.QuestionType;
import ru.vlk.book.store.agent.service.AgentMemoryService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
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
        entities.forEach(e -> System.out.println(e.toStringID()));
        return false;
    }
}
