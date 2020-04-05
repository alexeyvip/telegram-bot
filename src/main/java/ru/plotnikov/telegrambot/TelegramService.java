package ru.plotnikov.telegrambot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

@Service
public class TelegramService {

    private final String sendMessageUrl;
    private final URI getUpdatesUrl;
    private final String chatId;

    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).
            connectTimeout(Duration.ofSeconds(15)).build();

    @Autowired
    public TelegramService(@Value("${telegram-bot.api-key}") String apiKey, @Value("${telegram-bot.chat-id}") String chatId) {
        this.sendMessageUrl = String.format("https://api.telegram.org/bot%s/sendMessage", apiKey);
        this.getUpdatesUrl = URI.create(String.format("https://api.telegram.org/bot%s/getUpdates", apiKey));
        this.chatId = chatId;
    }

    public String sendMessage(Map<String, String> params) throws IOException, InterruptedException {
        params.put("chat_id", chatId);
        params.put("disable_web_page_preview", "1");
        HttpRequest request = HttpRequest.newBuilder().
                GET().
                uri(URI.create(sendMessageUrl + "?" + buildParams(params))).
                build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String getUpdates() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().
                GET().
                uri(getUpdatesUrl).
                build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    private static String buildParams(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        return builder.toString();
    }

}
