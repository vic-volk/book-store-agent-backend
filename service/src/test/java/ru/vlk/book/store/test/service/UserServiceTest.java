package ru.vlk.book.store.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.vlk.book.store.elastic.model.User;
import ru.vlk.book.store.service.UserService;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserService() {
        User user = userService.createUser("username", "test");
        assertNotNull(user);
    }
}
