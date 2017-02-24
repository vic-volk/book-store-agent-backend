package ru.vlk.book.store.agent.test;


import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "ru.vlk.book.store.elastic.repository")
@ImportResource(locations = {"classpath:META-INF/test-applicationContext-communication-agent.xml"})
public class TestAnnotationConfig {

    @Bean
    public NodeBuilder nodeBuilder() {
        return new NodeBuilder();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "elasticsearch").build();
        Client client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(
                        new InetSocketTransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
        return new ElasticsearchTemplate(client);
    }
}
