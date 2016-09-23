package ru.vlk.book.store.service;

import org.springframework.stereotype.Component;

@Component
public class EchoService {

    public String echo(String echo) {
        return echo;
    }
}
