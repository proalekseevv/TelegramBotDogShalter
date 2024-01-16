package ru.skypro.telegrambotdogshelter.services.impl;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.repository.UsersContactInfoRepository;
import ru.skypro.telegrambotdogshelter.services.interfaces.UsersContactInfoService;


@Service
public class UsersContactInfoServiceImpl implements UsersContactInfoService {

    private final TelegramBot telegramBot;
    private final UsersContactInfoRepository userContactInformationRepository;


    public UsersContactInfoServiceImpl(TelegramBot telegramBot, UsersContactInfoRepository userContactInformationRepository) {
        this.telegramBot = telegramBot;
        this.userContactInformationRepository = userContactInformationRepository;
    }
}



