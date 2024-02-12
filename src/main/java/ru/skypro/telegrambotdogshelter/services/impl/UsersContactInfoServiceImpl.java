package ru.skypro.telegrambotdogshelter.services.impl;

import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.exceptions.UserIdNotFoundException;
import ru.skypro.telegrambotdogshelter.models.Shelter;
import ru.skypro.telegrambotdogshelter.repository.ShelterRepository;
import ru.skypro.telegrambotdogshelter.repository.UsersContactInfoRepository;
import ru.skypro.telegrambotdogshelter.services.interfaces.UsersContactInfoService;
import ru.skypro.telegrambotdogshelter.models.UsersContactInformation;


@Service
@RequiredArgsConstructor
public class UsersContactInfoServiceImpl implements UsersContactInfoService {

    Logger logger = LoggerFactory.getLogger(UsersContactInfoService.class);

    private final ShelterRepository shelterRepository;

    private final UsersContactInfoRepository userContactInformationRepository;

    private final UsersContactInformation usersContactInformation;

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

    @Override
    public void saveUserInfo(Update update) {
        logger.info("Запущена обработка контактных данных.");
        long chatId = update.message().chat().id();

      //  createUserService(usersContactInformation);

        String userName = update.message().contact().firstName();
        String userSurname = update.message().contact().lastName();
        String userPhone = update.message().contact().phoneNumber();

        // переводим номер в лонг
        String userPhoneNumber = userPhone.substring(1);
        Long userPhoneLong = Long.parseLong(userPhoneNumber);

        UsersContactInformation newUser = new UsersContactInformation(null,
                chatId, userName, userSurname, userPhoneLong);

        //System.out.println(newUser);

        //shelterRepository.save(new Shelter(1L, "sh1"));

        if (userContactInformationRepository.existByPhoneNumber(userPhoneLong) == null) {


   userContactInformationRepository.save(newUser);}
       // System.out.println(userContactInformationRepository.findAll());


    }



}



