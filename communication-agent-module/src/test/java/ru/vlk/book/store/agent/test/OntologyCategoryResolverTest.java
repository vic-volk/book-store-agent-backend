package ru.vlk.book.store.agent.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.vlk.book.store.agent.model.QuestionType;
import ru.vlk.book.store.agent.lingvistic.CategoryResolver;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@Component
@ContextConfiguration("classpath*:META-INF/test-applicationContext-communication-agent.xml")
@EnableElasticsearchRepositories("ru.vlk.book.store.elastic")
public class OntologyCategoryResolverTest {

    @Inject
    private CategoryResolver categoryResolver;

    @Test
    public void testIsQuestion() {
        assertTrue(categoryResolver.isQuestion("do you have books about math"));
        assertTrue(categoryResolver.isQuestion("is there any book about Monte Christo?"));
        assertTrue(categoryResolver.isQuestion("do you have Cognitive enterprise book?"));
        assertFalse(categoryResolver.isQuestion("who are you?"));
    }

    @Test
    public void testResolveQuestionType() {
        assertEquals(QuestionType.Concrete,
                categoryResolver.resolveQuestionType("do you have Cognitive enterprise book?"));
        assertEquals(QuestionType.Category,
                categoryResolver.resolveQuestionType("do you have something about cognitive enterprise?"));
    }
}
