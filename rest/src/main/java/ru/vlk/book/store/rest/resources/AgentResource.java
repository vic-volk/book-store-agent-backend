package ru.vlk.book.store.rest.resources;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.agent.service.AgentService;
import ru.vlk.book.store.rest.model.Answer;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/agent")
@Component
public class AgentResource {

    @Inject
    private AgentService agentService;

    @GET
    @Produces("application/json")
    public Answer getAnswer() {
        Answer answer = new Answer();
        answer.setText(agentService.getCurrentQuestion().getMessage());
        return answer;
    }

    @POST
    public String pushQuestion(@QueryParam("question-text") String question) {
        question = StringUtils.isBlank(question) ? "" : question;
        agentService.handleQuestion(question);
        return "in handle";
    }

}
