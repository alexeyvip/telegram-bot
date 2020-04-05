package ru.plotnikov.telegrambot.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexEndpoint {

    @GetMapping("/")
    public String index() {
        return "hello from Github";
    }

}
