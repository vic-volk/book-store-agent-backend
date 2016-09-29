package ru.vlk.book.store.agent.service;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.elastic.model.Book;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;

@Component
public class AgentMemoryService {

    private OWLOntology ontology;

    public AgentMemoryService() throws OWLOntologyCreationException {
        File file = new File("D:\\data\\dev\\IdeaProjects\\rk9\\book-store-agent-backend\\" +
                "communication-agent-module\\src\\main\\resources\\ontology\\communication-ontology.owx");
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        this.ontology = manager.loadOntologyFromOntologyDocument(file);
    }

    private Page<Book> bookResults;

    @PostConstruct
    public void init() {
        bookResults = new PageImpl<>(new ArrayList<>());
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
}
