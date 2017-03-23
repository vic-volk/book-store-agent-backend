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

    @Test
    public void testOWLChatAgent() throws OWLOntologyStorageException, OWLOntologyCreationException, IOException {
        OWLDataFactory owlDataFactory = OWLManager.getOWLDataFactory();
        OWLOntologyManager owlOntologyManager = OWLManager.createOWLOntologyManager();

        IRI iri = IRI.create("http://www.semanticweb.org/");

        ///
        File output = File.createTempFile("sample_ontology.chat.", "owl");
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

        //модель общения
        //
        // handshake (не обязательная часть)
        // handshake
        // handshake --> handshake phrases
        //
        //              Hi, Hello, Hey, Hey man
        //              How’s it going? or
        //              How are you doing?
        //              What’s up?, What’s new?, or
        //              What’s going on?
        //              How’s everything ?,
        //              How are things?, or
        //              How’s life?
        //              How’s your day? or
        //              How’s your day going?
        //              Good to see you or
        //              Nice to see you
        //              Long time no see or
        //              It’s been a while
        //
        //              Good morning, Good afternoon, or Good evening
        //              It’s nice to meet you or Pleased to meet you
        //              How have you been?
        //              How do you do?
        //
        //              Yo!
        //              Are you OK?, You alright?, or Alright mate?
        //              Howdy!
        //              Sup? or Whazzup?
        //              G’day mate!
        //              Hiya!
        //
        //
        // messaging (обмен сообщениями, обязательная часть)
        // sentences
        // sentences --> sentence types:
        //                  - questions;
        //                  Is there any ${book.name} in your book store?
        //                  Is there any ${book.name} book?
        //                  Is there any ${book.name}?
        //                  Do you have ${book.name} book?
        //                  Do you have ${book.name}?
        //
        //                  - statements;
        //
        // closing (не обязательная часть)
        // closing --> closing phrases
        //
        // mis-understanding statements;
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
