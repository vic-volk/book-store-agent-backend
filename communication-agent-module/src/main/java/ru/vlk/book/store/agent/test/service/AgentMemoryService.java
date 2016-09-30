package ru.vlk.book.store.agent.test.service;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
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

@Component
public class AgentMemoryService {

    @Value("${ontology.file.location}")
    public String ontologyFilePath;

    private OWLOntology ontology;

    private Page<Book> bookResults;

    @PostConstruct
    public void init() throws AgentException {
        bookResults = new PageImpl<>(new ArrayList<>());
        loadOntology();
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

    private void loadOntology() throws AgentException {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        URL url = this.getClass().getClassLoader().getResource(ontologyFilePath);
        try {
            if (url == null) {
                throw new AgentException("Ontology file not found.");
            }
            File ontologyFile = new File(url.toURI());
            this.ontology = manager.loadOntologyFromOntologyDocument(ontologyFile);
        } catch (URISyntaxException e) {
            throw new AgentException("Exception in URI syntax: " + e.getMessage());
        } catch (OWLOntologyCreationException e) {
            throw new AgentException("Exception while creating ontology: " + e.getMessage());
        }
    }
}
