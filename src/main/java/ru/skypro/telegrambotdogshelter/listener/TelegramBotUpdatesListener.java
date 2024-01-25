package ru.skypro.telegrambotdogshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.services.interfaces.UsersContactInfoService;

import javax.annotation.PostConstruct;
import java.util.List;

import static ru.skypro.telegrambotdogshelter.enums.Responses.START;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    private UsersContactInfoService service;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            // Common greetind
            if(update.message().text().equals("/start")){
                long chatId = update.message().chat().id();
                logger.info("Received /start command from chatId: {}", chatId);
                String startMessage = START.getResponseText();
                telegramBot.execute(new SendMessage(chatId, startMessage));
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }






}
