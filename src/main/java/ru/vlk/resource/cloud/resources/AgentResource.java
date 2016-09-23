package ru.vlk.resource.cloud.resources;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import ru.vlk.resource.cloud.model.Answer;
import ru.vlk.resource.cloud.service.AgentService;

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
        answer.setText("answer");
        answer.setMessages(agentService.getMessages());
        return answer;
    }

    @POST
    public String handleQuestion(@QueryParam("question-text") String question) {
        question = StringUtils.isBlank(question) ? "" : question;
        agentService.rememberMessage(question);
        return "in handle";
    }

}
