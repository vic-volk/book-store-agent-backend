package ru.vlk.book.store.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.springframework.context.annotation.*;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Application {

    public static void main(String[] args) {
        JerseyConfig jerseyConfig = new JerseyConfig();
        jerseyConfig.property("contextConfig", new AnnotationConfigApplicationContext(AnnotationConfig.class));
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, jerseyConfig);
    }
}