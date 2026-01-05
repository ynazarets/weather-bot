package weather.weathertelegrambot.service;

import weather.weathertelegrambot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final WeatherService weatherService;

    public TelegramBot(BotConfig botConfig, WeatherService weatherService) {
        this.botConfig = botConfig;
        this.weatherService = weatherService;
        System.out.println("БОТ УСПЕШНО ИНИЦИАЛИЗИРОВАН В SPRING!");
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            if (messageText.startsWith("/start")) {
                sendMessage(chatId, "Привет. Напиши название города"
                        + " и я пришлю погоду");
            } else {
                String weather = weatherService.getWeather(messageText);
                sendMessage(chatId, weather);
            }
        }
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
