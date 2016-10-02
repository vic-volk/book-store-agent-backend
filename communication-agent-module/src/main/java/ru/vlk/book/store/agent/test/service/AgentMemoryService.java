package ru.vlk.book.store.agent.test.service;

import com.google.common.collect.Multimap;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.agent.test.exception.AgentException;
import ru.vlk.book.store.elastic.model.Book;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class AgentMemoryService {

    @Value("${ontology.file.location}")
    private String ontologyFilePath;

    private Set<String> questionPatterns;

    private OWLOntology ontology;

    private Page<Book> bookResults;

    @PostConstruct
    public void init() throws AgentException {
        bookResults = new PageImpl<>(new ArrayList<>());
        this.ontology = loadOntology();
        this.questionPatterns = loadQuestionPatterns(ontology);
    }

    public void setBookResults(Page<Book> bookResults) {
        this.bookResults = bookResults;
    }

    public Page<Book> getBookResults() {
        return this.bookResults;
    }

    public OWLOntology getOntology() {
        return ontology;
    }

    public Set<String> getQuestionPatterns() {
        return questionPatterns;
    }

    public void setQuestionPatterns(Set<String> questionPatterns) {
        this.questionPatterns = questionPatterns;
    }

    private OWLOntology loadOntology() throws AgentException {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        URL url = this.getClass().getClassLoader().getResource(ontologyFilePath);
        try {
            if (url == null) {
                throw new AgentException("Ontology file not found.");
            }
            File ontologyFile = new File(url.toURI());
            return manager.loadOntologyFromOntologyDocument(ontologyFile);
        } catch (URISyntaxException e) {
            throw new AgentException("Exception in URI syntax: " + e.getMessage());
        } catch (OWLOntologyCreationException e) {
            throw new AgentException("Exception while creating ontology: " + e.getMessage());
        }
    }

    private Set<String> loadQuestionPatterns(OWLOntology ontology) {
        Set<OWLClass> classes = ontology.classesInSignature().collect(Collectors.toSet());
        OWLClass questionPatternClass = classes.stream()
                .filter(cl -> classNameEquals(cl, "QuestionPattern", ontology)).findFirst().get();
        Set<OWLIndividual> individuals = EntitySearcher.getIndividuals(questionPatternClass, ontology)
                .collect(Collectors.toSet());
        return getPatternValues(individuals, ontology);
    }

    private boolean classNameEquals(OWLClass owlClass, String name, OWLOntology owlOntology) {
        Set<OWLAnnotation> annotations = EntitySearcher.getAnnotations(owlClass, owlOntology)
                .filter(a -> a.getValue().asLiteral().get().getLiteral().equals(name))
                .collect(Collectors.toSet());
        if (annotations.size() == 0) {
            return false;
        }
        return annotations.iterator().next() != null;
    }

    private Set<String> getPatternValues(Set<OWLIndividual> individuals, OWLOntology ontology) {
        Set<String> values = new HashSet<>();
        individuals.forEach(i -> {
            Multimap<OWLDataPropertyExpression, OWLLiteral> propertiesForIndividual =
                    EntitySearcher.getDataPropertyValues(i, ontology);
            propertiesForIndividual.asMap().values().forEach(
                    v -> v.forEach(val ->
                            values.add(val.getLiteral())));
        });
        return values;
    }
}
