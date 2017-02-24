package ru.vlk.book.store.agent.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.vlk.book.store.agent.lingvistic.QuestionConverter;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@Component
@ContextConfiguration("classpath*:META-INF/test-applicationContext-communication-agent.xml")
@EnableElasticsearchRepositories("ru.vlk.book.store.elastic")
public class QuestionConverterTest {

    @Inject
    private QuestionConverter questionConverter;

    @Test
    public void testQuestionToQuery() {
        System.out.println(questionConverter.questionToQuery("do you have math book?"));
    }
}
