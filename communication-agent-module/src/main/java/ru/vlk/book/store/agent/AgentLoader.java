package ru.vlk.book.store.agent;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@Component
public class AgentLoader {

    Executor executor = new ForkJoinPool();

    @PostConstruct
    public void init() {
        System.out.println("init");
        executor.execute(() -> System.out.println("command executing!"));
    }
}
