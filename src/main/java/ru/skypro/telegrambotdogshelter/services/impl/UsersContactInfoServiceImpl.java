package ru.skypro.telegrambotdogshelter.services.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.exceptions.UserIdNotFoundException;
import ru.skypro.telegrambotdogshelter.repository.UsersContactInfoRepository;
import ru.skypro.telegrambotdogshelter.services.interfaces.UsersContactInfoService;
import ru.skypro.telegrambotdogshelter.models.UsersContactInformation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.slf4j.LoggerFactory.*;


@Service
@RequiredArgsConstructor
public class UsersContactInfoServiceImpl implements UsersContactInfoService {

    private final TelegramBot telegramBot;
    private final UsersContactInfoRepository userContactInformationRepository;
    private Logger logger = getLogger(UsersContactInfoServiceImpl.class);;
    private static final Pattern MESSAGE_PATTERN =
            Pattern.compile("([0-9\\.:\\s]){16}(\\s)(.+)");


    @Override
    public UsersContactInformation createUserService(UsersContactInformation usersContactInformation) {
        logger.info("Was invoked method for create userContactInformation");
        return userContactInformationRepository.save(usersContactInformation);
    }

@Override
    public UsersContactInformation readUserServiceById(long id) {
        if (!userContactInformationRepository.existsById(id))
            logger.error("Пользователь c id {} не найден", id);
        return userContactInformationRepository.findById(id)
                .orElseThrow(() ->
                        new UserIdNotFoundException("Пользователь не найден"));
    }


    // Сохранение контактных данных пользователя
    @Override
    public void saveUserInfo(Update update) {
        long chatId = update.message().chat().id();


        // если отправлено пустое сообщение.
        if (update.message() == null) {
            logger.info("Отправлено пустое сообщение.");
            return;
        }

        String userFirstName = update.message().contact().firstName();
        String userLastName = update.message().contact().lastName();
        String userPhone = update.message().contact().phoneNumber();


        // Проверка сообщения на соответствие регулярному выражению

        // ДОРАБОТАТЬ ЛОГИКУ ОБРАБОТКИ

        if (matcher.find()) {
            telegramBot.execute(new SendMessage(chatId,
                    "Данные успешно записаны."));
        } else {
            telegramBot.execute(new SendMessage(chatId,
                    "Некорректные данные. Введите фамилию, имя и номер телефона."));
        }


    }


}



