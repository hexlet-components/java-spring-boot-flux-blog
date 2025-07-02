package io.hexlet;

import io.hexlet.model.User;
import org.springframework.test.web.reactive.server.WebTestClient;

public class TestUtils {

    public static int getUserId(String email, WebTestClient client) {
        return (int) client
                .get()
                .uri("/users").exchange()
                .returnResult(User.class).getResponseBody().toStream()
                .filter(user -> user.getEmail().equals(email))
                .findAny().get().getId();
    }
}
