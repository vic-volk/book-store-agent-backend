package ru.vlk.book.store.service;

import ru.vlk.book.store.elastic.model.User;

public interface SessionManager {

    String getSession(User user, String password);

    String renewSession(User user, String password);

    void closeSession(User user, String sessionId);
}
