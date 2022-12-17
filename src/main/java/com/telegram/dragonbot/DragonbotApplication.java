package com.telegram.dragonbot;

import com.telegram.dragonbot.service.bot.Dragonbot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@SpringBootConfiguration
public class DragonbotApplication {

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Dragonbot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        SpringApplication.run(DragonbotApplication.class, args);
    }

}
