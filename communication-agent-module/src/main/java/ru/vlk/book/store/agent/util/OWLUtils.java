package ru.vlk.book.store.agent.util;

import com.google.common.collect.Multimap;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class OWLUtils {

    public static OWLClass getOWLClass(String name, OWLOntology ontology) {
        Set<OWLClass> classes = ontology.classesInSignature().collect(Collectors.toSet());
        return classes.stream()
                .filter(cl -> classNameEquals(cl, name, ontology)).findFirst().get();
    }

    public static boolean classNameEquals(OWLClass owlClass, String name, OWLOntology owlOntology) {
        Set<OWLAnnotation> annotations = EntitySearcher.getAnnotations(owlClass, owlOntology)
                .filter(a -> a.getValue().asLiteral().get().getLiteral().equals(name))
                .collect(Collectors.toSet());
        if (annotations.size() == 0) {
            return false;
        }
        return annotations.iterator().next() != null;
    }

    public static Set<OWLIndividual> getClassIndividuals(OWLClass owlClass, OWLOntology ontology) {
        return EntitySearcher.getIndividuals(owlClass, ontology)
                .collect(Collectors.toSet());
    }

    public static Set<String> getIndividualValues(Set<OWLIndividual> individuals, OWLOntology ontology) {
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
