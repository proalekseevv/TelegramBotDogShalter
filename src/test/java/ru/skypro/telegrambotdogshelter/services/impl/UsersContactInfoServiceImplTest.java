package ru.skypro.telegrambotdogshelter.services.impl;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.telegrambotdogshelter.botMenu.BotManagementService;
import ru.skypro.telegrambotdogshelter.models.UsersContactInformation;
import ru.skypro.telegrambotdogshelter.repository.UsersContactInfoRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersContactInfoServiceImplTest {

    @InjectMocks
    UsersContactInfoServiceImpl usersContactInfoService;

    @Mock
    UsersContactInfoRepository usersContactInfoRepository;
    @Mock
    UsersContactInformation usersContactInformation;
    @Mock
    BotManagementService service;

    @Test
    void createUserService() {
        UsersContactInformation expected = UsersContactInformation.builder()
                .id(1L).name("Fil").build();
        when(usersContactInfoRepository.save(any(UsersContactInformation.class)))
                .thenReturn(expected);

        //test
        UsersContactInformation actual = usersContactInfoService.createUserService(expected);

        //check
        assertThat(actual).isEqualTo(expected);
        verify(usersContactInfoRepository, only()).save(any(UsersContactInformation.class));
    }

    @Test
    void readUserServiceById() {
        UsersContactInformation expected = UsersContactInformation.builder()
                .id(1L).name("Fil").build();
        when(usersContactInfoRepository.findById(expected.getId()))
                .thenReturn(Optional.of(expected));

        //test
        UsersContactInformation actual = usersContactInfoService.readUserServiceById(expected.getId());

        //check
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void saveUserInfo_wrongNumberFormat() {
        Update update = setUp(1L, "", "", "sdfghjkl");
        //test&check
        assertThrows(NumberFormatException.class, () -> usersContactInfoService.saveUserInfo(update));
    }

    @Test
    void saveUserInfo() {
        UsersContactInformation expected = UsersContactInformation.builder()
                .id(1L).name("Fil").surname("B").phoneNumber(79876543210L).build();
        when(usersContactInfoRepository.save(expected))
                .thenReturn(expected);

        //test
        UsersContactInformation actual = usersContactInfoService.createUserService(expected);

        //check
        assertThat(actual).isEqualTo(expected);
        verify(usersContactInfoRepository, only()).save(any(UsersContactInformation.class));
    }

    private Update setUp(Long chatId, String firstName, String lastName, String phoneNumber) {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        when(update.message()).thenReturn(message);
        Chat chat = mock(Chat.class);
        when(message.chat()).thenReturn(chat);
        Contact contact = mock(Contact.class);
        when(message.contact()).thenReturn(contact);

        when(chat.id()).thenReturn(chatId);
        when(contact.firstName()).thenReturn(firstName);
        when(contact.lastName()).thenReturn(lastName);
        when(contact.phoneNumber()).thenReturn(phoneNumber);
        return update;
    }

}