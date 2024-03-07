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
import ru.skypro.telegrambotdogshelter.repository.UsersRepository;
import ru.skypro.telegrambotdogshelter.services.interfaces.UsersService;
import ru.skypro.telegrambotdogshelter.models.Users;


@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    Logger logger = LoggerFactory.getLogger(UsersService.class);

    private final UsersRepository userContactInformationRepository;
    private final ShelterRepository shelterRepository;
    private final Users users;
    private final TelegramBot telegramBot;
    private final BotManagementService service;


    @Override
    public Users createUserService(Users users) {
        logger.info("Was invoked method for create userContactInformation");
        return userContactInformationRepository.save(users);
    }

    @Override
    public Users readUserServiceById(long id) {
        if (!userContactInformationRepository.existsById(id))
            logger.error("Пользователь c id {} не найден", id);
        return userContactInformationRepository.findById(id)
                .orElseThrow(() ->
                        new UserIdNotFoundException("Пользователь не найден"));
    }

//    @Override

    /**
     * Метод для обработки отправленных контактных данных от пользователя.
     *
     * @param update Объект обновления бота, содержащий информацию о действиях пользователя.
     */
    public void saveUserInfo(Update update) {
        logger.info("Запущена обработка контактных данных.");

        // Сбор информации о пользователе из отправленного по кнопке: имя, фамилия и телефон
        long chatId = update.message().chat().id();

        String userName = update.message().contact().firstName();
        String userSurname = update.message().contact().lastName();
        String userPhone = update.message().contact().phoneNumber();
        // переводим номер в лонг
        String userPhoneNumber = userPhone.substring(1);
        Long userPhoneLong = Long.parseLong(userPhoneNumber);

        // Сохранение данных контакта в БД
        Users newUser = new Users(null,
                chatId, userName, userSurname, userPhoneLong);

        if (userContactInformationRepository.existByPhoneNumber(userPhoneLong) == null) {
            userContactInformationRepository.save(newUser);
            // Информация о записанных данных и возврат в меню.
            telegramBot.execute(new SendMessage(chatId,
                    "Ваши данные успешно записаны."));
            service.sendBackToSheltersButton2(chatId);

        } else {
            telegramBot.execute(new SendMessage(chatId,
                    "Такой пользователь уже записан."));
            service.sendBackToSheltersButton2(chatId);
        }


    }
}



