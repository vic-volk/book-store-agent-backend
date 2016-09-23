package ru.vlk.resource.cloud.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AgentService {

    Map<Date, String> messages;

    @PostConstruct
    public void init() {
        messages = new HashMap<>();
    }

    public void rememberMessage(String message) {
        messages.put(new Date(), message);
    }

    public Map<Date, String> getMessages() {
        return messages;
    }
}
