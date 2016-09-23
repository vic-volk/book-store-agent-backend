package ru.vlk.book.store.agent;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AgentLoader {

    @PostConstruct
    public void init() {
        System.out.println("init");
    }
}
