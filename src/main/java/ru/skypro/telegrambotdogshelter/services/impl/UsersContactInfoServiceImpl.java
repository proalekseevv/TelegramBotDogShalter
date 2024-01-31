package ru.skypro.telegrambotdogshelter.services.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.repository.UsersContactInfoRepository;
import ru.skypro.telegrambotdogshelter.services.interfaces.UsersContactInfoService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UsersContactInfoServiceImpl implements UsersContactInfoService {

    private final TelegramBot telegramBot;
    private final UsersContactInfoRepository userContactInformationRepository;

    private Logger logger = LoggerFactory.getLogger(UsersContactInfoServiceImpl.class);
    private static final Pattern MESSAGE_PATTERN =
            Pattern.compile("([0-9\\.:\\s]){16}(\\s)(.+)");


    public UsersContactInfoServiceImpl(TelegramBot telegramBot, UsersContactInfoRepository userContactInformationRepository) {
        this.telegramBot = telegramBot;
        this.userContactInformationRepository = userContactInformationRepository;
    }

    // Сохранение контактных данных пользователя
    @Override
    public void saveUserInfo(Update update) {
        // если отправлено пустое сообщение.
        if (update.message() == null) {
            logger.info("Отправлено пустое сообщение.");
            return;
        }
        long chatId = update.message().chat().id();
        String userMessage = update.message().text();

        if (userMessage == null) {
            telegramBot.execute(new SendMessage(chatId,
                    "Нужно ввести фамилию и имя русскими буквами."));
            return;
        }

        Matcher matcher = MESSAGE_PATTERN.matcher(userMessage);

        if (matcher.find()) {
            telegramBot.execute(new SendMessage(chatId,
                    "Данные успешно записаны."));
        } else {
            telegramBot.execute(new SendMessage(chatId,
                    "Некорректные данные. Введите фамилию и имя."));
            return;
        }

       // String surname = matcher.group(1);
       // String name = matcher.group(3);

        return;
    }


}



