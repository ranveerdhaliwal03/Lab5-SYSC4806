package com.example.lab5;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressBookIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void addressBookShouldReturnListOfBuddies() {
        String url = "http://localhost:" + port + "/addressBooks/1/buddies";
        String body = restTemplate.getForObject(url, String.class);
        assertThat(body).contains("Alice").contains("Bob");
    }
}

