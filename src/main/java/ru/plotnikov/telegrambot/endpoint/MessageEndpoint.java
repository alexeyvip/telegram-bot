package ru.plotnikov.telegrambot.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.plotnikov.telegrambot.TelegramService;

import java.io.IOException;
import java.util.Map;

@RestController
public class MessageEndpoint {

    private final TelegramService telegramService;

    @Autowired
    public MessageEndpoint(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @GetMapping("/message/send")
    public String messageSend(@RequestParam Map<String, String> params) {
        return "FAIL";
    }

    @GetMapping("/getUpdates")
    public String getUpdates() throws IOException, InterruptedException {
        return telegramService.getUpdates();
    }
}
