package ru.vlk.book.store.agent.test;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.vlk.book.store.agent.test.lingvistic.QuestionConverter;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@Component
@ContextConfiguration("classpath*:META-INF/test-applicationContext-communication-agent.xml")
@EnableElasticsearchRepositories("ru.vlk.book.store.elastic")
public class QuestionConverterTest {

    @Bean
    public NodeBuilder nodeBuilder() {
        return new NodeBuilder();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        Settings.Builder elasticsearchSettings =
                Settings.settingsBuilder()
                        .put("http.enabled", "true")
                        .put("path.home", "D:\\data\\soft\\ELK\\elasticsearch-2.2.0\\elasticsearch-2.2.0")
                        .put("path.data", "D:\\elasticDB");
        return new ElasticsearchTemplate(nodeBuilder()
                .local(true)
                .settings(elasticsearchSettings.build())
                .node()
                .client());
    }

    @Inject
    private QuestionConverter questionConverter;

    @Test
    public void testQuestionToQuery() {
        System.out.println(questionConverter.questionToQuery("do you have math book?"));
    }
}
