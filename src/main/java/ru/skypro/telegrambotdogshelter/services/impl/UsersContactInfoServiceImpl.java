package ru.skypro.telegrambotdogshelter.services.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.botMenu.BotManagementService;
import ru.skypro.telegrambotdogshelter.exceptions.UserIdNotFoundException;
import ru.skypro.telegrambotdogshelter.repository.ShelterRepository;
import ru.skypro.telegrambotdogshelter.repository.UsersContactInfoRepository;
import ru.skypro.telegrambotdogshelter.services.interfaces.UsersContactInfoService;
import ru.skypro.telegrambotdogshelter.models.UsersContactInformation;


@Service
@RequiredArgsConstructor
public class UsersContactInfoServiceImpl implements UsersContactInfoService {

    Logger logger = LoggerFactory.getLogger(UsersContactInfoService.class);

    private final UsersContactInfoRepository userContactInformationRepository;
    private final ShelterRepository shelterRepository;
    private final UsersContactInformation usersContactInformation;
    private final TelegramBot telegramBot;
    private final BotManagementService service;


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

//    @Override
    public void saveUserInfo(Update update) {
        logger.info("Запущена обработка контактных данных.");

        long chatId = update.message().chat().id();

        String userName = update.message().contact().firstName();
        String userSurname = update.message().contact().lastName();
        String userPhone = update.message().contact().phoneNumber();
        // переводим номер в лонг
        String userPhoneNumber = userPhone.substring(1);
        Long userPhoneLong = Long.parseLong(userPhoneNumber);

        // Сохранение данных контакта в БД
        UsersContactInformation newUser = new UsersContactInformation(null,
                chatId, userName, userSurname, userPhoneLong);

        if (userContactInformationRepository.existByPhoneNumber(userPhoneLong) == null) {
            userContactInformationRepository.save(newUser);
            // Информация о записанных данных и возврат в меню.
            telegramBot.execute(new SendMessage(chatId,
                    "Ваши данные успешно записаны."));
            service.sendBackToSheltersButton2(chatId);

        } else
        {
            telegramBot.execute(new SendMessage(chatId,
                    "Такой пользователь уже записан."));
            service.sendBackToSheltersButton2(chatId);
        }


    }
}



