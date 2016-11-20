package ru.vlk.book.store.rest;

import com.sun.net.httpserver.HttpServer;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.ws.rs.core.UriBuilder;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "ru.vlk.book.store.elastic.repository")
public class Application extends ResourceConfig {

    public Application() {
        packages("ru.vlk.book.store.rest.resources");
        property("contextConfigLocation", "classpath:META-INF/applicationContext.xml");
        register(JacksonFeature.class);
    }

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

    public static void main(String[] args) {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, new Application());
    }
}