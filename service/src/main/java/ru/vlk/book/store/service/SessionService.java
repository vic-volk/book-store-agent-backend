package ru.vlk.book.store.service;

import org.springframework.stereotype.Component;
import ru.vlk.book.store.elastic.model.User;

import java.util.Map;

@Component
public class SessionService implements SessionManager {

    private Map<User,String> sessionMap;

    @Override
    public String getSession(User user, String password) {
        return null;
    }

    @Override
    public String renewSession(User user, String password) {
        return null;
    }

    @Override
    public void closeSession(User user, String sessionId) {

    }
}
