package ru.vlk.book.store.rest.resources;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.agent.service.AgentService;
import ru.vlk.book.store.elastic.model.Book;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/agent")
@Component
public class AgentResource {

    @Inject
    private AgentService agentService;

    @GET
    @Produces("application/json")
    public Page<Book> getAnswer() {
        return agentService.getBookResults();
    }

    @POST
    public String pushQuestion(@QueryParam("question-text") String question) {
        question = StringUtils.isBlank(question) ? "" : question;
        agentService.handleQuestion(question);
        return "in handle";
    }
}
