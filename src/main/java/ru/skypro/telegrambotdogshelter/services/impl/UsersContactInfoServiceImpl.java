package ru.skypro.telegrambotdogshelter.services.impl;

import com.pengrad.telegrambot.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.exceptions.UserIdNotFoundException;
import ru.skypro.telegrambotdogshelter.repository.UsersContactInfoRepository;
import ru.skypro.telegrambotdogshelter.services.interfaces.UsersContactInfoService;
import ru.skypro.telegrambotdogshelter.models.UsersContactInformation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class UsersContactInfoServiceImpl implements UsersContactInfoService {

    private final TelegramBot telegramBot;
    private final UsersContactInfoRepository userContactInformationRepository;
    private Logger logger;


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


}



