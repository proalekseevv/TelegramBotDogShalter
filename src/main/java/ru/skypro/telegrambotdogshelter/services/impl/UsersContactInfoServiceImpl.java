package ru.skypro.telegrambotdogshelter.services.impl;

import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.exceptions.UserIdNotFoundException;
import ru.skypro.telegrambotdogshelter.repository.UsersContactInfoRepository;
import ru.skypro.telegrambotdogshelter.services.interfaces.UsersContactInfoService;
import ru.skypro.telegrambotdogshelter.models.UsersContactInformation;


@Service
@RequiredArgsConstructor
public class UsersContactInfoServiceImpl implements UsersContactInfoService {

    Logger logger = LoggerFactory.getLogger(UsersContactInfoService.class);

    private final UsersContactInfoRepository userContactInformationRepository;



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

        long chatId = update.message().chat().id();

        String userName = update.message().contact().firstName();
        String userSurname = update.message().contact().lastName();
        String userPhone = update.message().contact().phoneNumber();


// вот здесь ругается на тип сохраняемых данных. Вроде понимаю, что нужны данные типа UsersContactInformation,
// но не могу понять, как эти три параметра привести к типу данных не стринг, а юзерконтактинфо...
//        userContactInformationRepository.save(userName);
//        userContactInformationRepository.save(userSurname);
//        userContactInformationRepository.save(userPhone);


    }
}



