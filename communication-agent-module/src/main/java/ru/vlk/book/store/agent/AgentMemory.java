package ru.vlk.book.store.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AgentMemory {

    private List<Task> tasks;

    private List<Function> interfaces;

    public AgentMemory(List<Function> interfaces) {
        this.interfaces = interfaces;
        this.tasks = new ArrayList<>();
    }
}
