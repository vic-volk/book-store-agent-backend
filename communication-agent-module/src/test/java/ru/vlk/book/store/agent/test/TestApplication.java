package ru.vlk.book.store.agent.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "ru.vlk.book.store.elastic.repository")
public class TestApplication {


}
