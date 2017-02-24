package ru.vlk.book.store.agent.test;

import com.google.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.semanticweb.owlapi.model.OWLOntology;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.vlk.book.store.agent.test.service.AgentMemoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@Component
@ContextConfiguration("classpath*:META-INF/test-applicationContext-communication-agent.xml")
@EnableElasticsearchRepositories("ru.vlk.book.store.elastic")
public class AgentMemoryServiceTest {

    @Inject
    private AgentMemoryService agentMemoryService;

    @Test
    public void testOWL() {
        OWLOntology owlOntology = agentMemoryService.getOntology();
    }
}
