package ru.vlk.book.store.agent.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Component
@ContextConfiguration(classes = TestAnnotationConfig.class)
@EnableElasticsearchRepositories("ru.vlk.book.store.elastic")
public class AgentMemoryServiceTest {
}
