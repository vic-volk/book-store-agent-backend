package ru.vlk.book.store.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import ru.vlk.book.store.elastic.ElasticConfig;

@Configuration
@ImportResource(locations = {"classpath:META-INF/applicationContext.xml"})
@Import(ElasticConfig.class)
public class AnnotationConfig {
}
