package ru.vlk.book.store.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import ru.vlk.book.store.rest.filters.CORSResponseFilter;

public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("ru.vlk.book.store.rest.resources");
        register(JacksonFeature.class);
        register(CORSResponseFilter.class);
    }
}
