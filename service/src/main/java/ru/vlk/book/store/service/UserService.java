package ru.vlk.book.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vlk.book.store.elastic.model.User;
import ru.vlk.book.store.elastic.repository.UserRepository;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String password) {
        User user = new User(username, password);
        return userRepository.save(user);
    }
}
