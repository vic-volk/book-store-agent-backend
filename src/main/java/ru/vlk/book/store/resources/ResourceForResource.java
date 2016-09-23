package ru.vlk.book.store.resources;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.model.Resource;
import ru.vlk.book.store.service.ResourceService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/resource")
@Component
public class ResourceForResource {

    @Inject
    private ResourceService resourceService;

    @GET
    @Produces("application/json")
    public Page<Resource> testRetrieving() {
        return resourceService.listAll();
    }

    @POST
    public String testIndexing() {
        resourceService.indexSample();
        return "success";
    }
}
