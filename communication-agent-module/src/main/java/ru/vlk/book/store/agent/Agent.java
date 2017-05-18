package ru.vlk.book.store.agent;

import java.util.List;

public abstract class Agent implements Runnable {

    private AgentMemory memory;

    public Agent(List<Interface> interfaces) {
        this.memory = new AgentMemory(interfaces);
    }

    public void run() {
        performTasks();
    }

    public abstract void performTasks();
}
