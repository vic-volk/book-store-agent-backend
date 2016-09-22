package ru.vlk.resource.cloud;

import com.sun.net.httpserver.HttpServer;
import org.elasticsearch.common.settings.Settings;
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
import java.net.URI;

@Configuration
@EnableElasticsearchRepositories(basePackages = "ru.vlk.resource.cloud.repository")
public class Application extends ResourceConfig {

    public Application() {
        packages("ru.vlk.resource.cloud.resources");
        property("contextConfigLocation", "classpath:META-INF/applicationContext.xml");
        register(JacksonFeature.class);
    }

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

    public static void main(String[] args) {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, new Application());
    }
}