package ru.vlk.book.store.rest.resources;

import org.springframework.stereotype.Component;
import ru.vlk.book.store.rest.service.EchoService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/echo")
@Component
public class EchoResource {

    @Inject
    private EchoService echoService;

    @GET
    @Path("/{echo}")
    @Produces("text/plain")
    public String echo(@PathParam("echo")String echo) {
        return echoService.echo(echo);
    }
}
