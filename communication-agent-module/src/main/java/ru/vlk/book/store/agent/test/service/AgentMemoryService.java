package ru.vlk.book.store.agent.test.service;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
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
import java.util.Set;

import static ru.vlk.book.store.agent.test.util.OWLUtils.*;

@Component
public class AgentMemoryService {

    @Value("${ontology.file.location}")
    private String ontologyFilePath;

    private Set<String> questionPatterns;

    private Set<String> categoryQuestionWords;

    private OWLOntology ontology;

    private Page<Book> bookResults;

    @PostConstruct
    public void init() throws AgentException {
        bookResults = new PageImpl<>(new ArrayList<>());
        this.ontology = loadOntology();
        this.questionPatterns = loadQuestionPatterns(ontology);
        this.categoryQuestionWords = loadCategoryQuestionWords(ontology);
    }

    public void setBookResults(Page<Book> bookResults) {
        this.bookResults = bookResults;
    }

    public Page<Book> getBookResults() {
        return this.bookResults;
    }

    public Set<String> getQuestionPatterns() {
        return questionPatterns;
    }

    public Set<String> getCategoryQuestionWords() {
        return categoryQuestionWords;
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
        return getValuesOfClassIndividuals("QuestionPattern", ontology);
    }

    private Set<String> loadCategoryQuestionWords(OWLOntology ontology) {
        return getValuesOfClassIndividuals("CategoryQuestionPattern", ontology);
    }

    private Set<String> getValuesOfClassIndividuals(String name, OWLOntology ontology) {
        OWLClass owlClass = getOWLClass(name, ontology);
        Set<OWLIndividual> individuals = getClassIndividuals(owlClass, ontology);
        return getIndividualValues(individuals, ontology);
    }

    public OWLOntology getOntology() {
        return ontology;
    }
}
