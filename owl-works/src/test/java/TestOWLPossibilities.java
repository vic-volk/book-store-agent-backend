import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import java.io.File;
import java.io.IOException;

public class TestOWLPossibilities {

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
        owlOntologyManager.applyChange(addAxiom);

        owlOntologyManager.saveOntology(o);

        OWLClass skill = owlDataFactory.getOWLClass(IRI.create(iri + "#skill"));
        OWLClass knowledge = owlDataFactory.getOWLClass(IRI.create(iri + "#knowledge"));
        OWLClass student = owlDataFactory.getOWLClass(IRI.create(iri + "#student"));

        //умеет составлять и решать дифференциальные уравнения,
        OWLIndividual differentialEquationsSkills =
                owlDataFactory.getOWLNamedIndividual(IRI.create(iri + "#knowledge-differential"));
        //обладает знаниями в теории вероятностей
        OWLIndividual probabilityTheoryKnowledges =
                owlDataFactory.getOWLNamedIndividual(IRI.create(iri + "#knowledge-probability"));
        //обладает знаниями в математической статистике
        OWLIndividual mathStatisticKnowledges =
                owlDataFactory.getOWLNamedIndividual(IRI.create(iri + "#knowledge-statistics"));
        //обладает знаниями в проектировании программных систем
        OWLIndividual systemDesignKnowledges =
                owlDataFactory.getOWLNamedIndividual(IRI.create(iri + "#knowledge-system-design"));
        //обладает знаниями по алгоритмам и структурам данных
        OWLIndividual algorithmsKnowledges =
                owlDataFactory.getOWLNamedIndividual(IRI.create(iri + "#knowledge-algorithms"));
        //умеет писать программы на языках высокого уровня
        OWLIndividual programmingSkills =
                owlDataFactory.getOWLNamedIndividual(IRI.create(iri + "#skill-programming"));

        axiom = owlDataFactory.getOWLClassAssertionAxiom(skill, differentialEquationsSkills);
        addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);
        axiom = owlDataFactory.getOWLClassAssertionAxiom(skill, programmingSkills);
        addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);
        axiom = owlDataFactory.getOWLClassAssertionAxiom(knowledge, probabilityTheoryKnowledges);
        addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);
        axiom = owlDataFactory.getOWLClassAssertionAxiom(knowledge, mathStatisticKnowledges);
        addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);
        axiom = owlDataFactory.getOWLClassAssertionAxiom(knowledge, systemDesignKnowledges);
        addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);
        axiom = owlDataFactory.getOWLClassAssertionAxiom(knowledge, algorithmsKnowledges);
        addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);

        owlOntologyManager.saveOntology(o);

        OWLIndividual vlk = owlDataFactory.getOWLNamedIndividual(IRI.create(iri + "#vlk"));
        axiom = owlDataFactory.getOWLClassAssertionAxiom(student, vlk);
        addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);

        owlOntologyManager.saveOntology(o);

        OWLObjectPropertyExpression knows = owlDataFactory.getOWLObjectProperty(IRI.create(iri + "#knows"));
        OWLObjectPropertyExpression haveSkill = owlDataFactory.getOWLObjectProperty(IRI.create(iri + "#have-skill"));
        axiom = owlDataFactory.getOWLObjectPropertyAssertionAxiom(knows, vlk, algorithmsKnowledges);
        addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);
        axiom = owlDataFactory.getOWLObjectPropertyAssertionAxiom(knows, vlk, systemDesignKnowledges);
        addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);
        axiom = owlDataFactory.getOWLObjectPropertyAssertionAxiom(knows, vlk, mathStatisticKnowledges);
        addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);
        axiom = owlDataFactory.getOWLObjectPropertyAssertionAxiom(knows, vlk, probabilityTheoryKnowledges);
        addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);
        axiom = owlDataFactory.getOWLObjectPropertyAssertionAxiom(haveSkill, vlk, differentialEquationsSkills);
        addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);
        axiom = owlDataFactory.getOWLObjectPropertyAssertionAxiom(haveSkill, vlk, programmingSkills);
        addAxiom = new AddAxiom(o, axiom);
        owlOntologyManager.applyChange(addAxiom);

        owlOntologyManager.saveOntology(o);
    }

    //добавить класс в онтологию
    //

    //добавить подкласс персоны
    //добавить студента
    //добавить область знаний
    //добавить навык
    //добавить информацию о том, что студент обладает или знает что-нибудь
    //добавить информацию о том, что студент должен знать
}
