package ru.vlk.resource.cloud.service;

import org.springframework.stereotype.Component;

@Component
public class EchoService {

    public String echo(String echo) {
        return echo;
    }
}
