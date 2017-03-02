package ru.vlk.book.store.agent.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.vlk.book.store.agent.service.AgentMemoryService;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@Component
@ContextConfiguration(classes = TestAnnotationConfig.class)
@EnableElasticsearchRepositories("ru.vlk.book.store.elastic")
public class AgentMemoryServiceTest {

    @Inject
    private AgentMemoryService agentMemoryService;

    @Test
    public void testOWL() throws OWLOntologyStorageException, OWLOntologyCreationException, IOException {
        OWLDataFactory owlDataFactory = OWLManager.getOWLDataFactory();
        OWLOntologyManager owlOntologyManager = OWLManager.createOWLOntologyManager();

        IRI iri = IRI.create("http://www.semanticweb.org/");

        ///
        File output = File.createTempFile("sample_ontology.", "owl");
        IRI documentIRI = IRI.create(output);
        SimpleIRIMapper mapper = new SimpleIRIMapper(iri, documentIRI);
        owlOntologyManager.getIRIMappers().add(mapper);
        File localFolder = new File("materializedOntologies");
        owlOntologyManager.getIRIMappers().add(new AutoIRIMapper(localFolder, true));
        OWLOntology o = owlOntologyManager.createOntology(iri);

        OWLClass clsA = owlDataFactory.getOWLClass(IRI.create(iri + "#A"));
        OWLClass clsB = owlDataFactory.getOWLClass(IRI.create(iri + "#B"));

        OWLAxiom axiom = owlDataFactory.getOWLSubClassOfAxiom(clsA, clsB);
        AddAxiom addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);

        OWLIndividual owlIndividual1 = owlDataFactory.getOWLNamedIndividual(IRI.create(iri + "#C"));
        OWLIndividual owlIndividual2 = owlDataFactory.getOWLNamedIndividual(IRI.create(iri + "#E"));
        OWLObjectPropertyExpression expression = owlDataFactory.getOWLObjectProperty(IRI.create(iri + "#F"));
        axiom = owlDataFactory.getOWLObjectPropertyAssertionAxiom(expression, owlIndividual1, owlIndividual2);

        addAxiom = new AddAxiom(o, axiom);

        owlOntologyManager.saveOntology(o);
    }
}
