package ru.skypro.telegrambotdogshelter.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.telegrambotdogshelter.models.UsersContactInformation;
import ru.skypro.telegrambotdogshelter.repository.UsersContactInfoRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersContactInfoServiceImplTest {

    @InjectMocks
    UsersContactInfoServiceImpl usersContactInfoService;

    @Mock
    UsersContactInfoRepository usersContactInfoRepository;

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
    void saveUserInfo() {
//        Update update = mock(Update.class);
//        when(update.message().chat().id()).thenReturn(1L);
//        when(update.message().contact().firstName()).thenReturn("userName");
//        when(update.message().contact().lastName()).thenReturn("userSurname");
//        when(update.message().contact().phoneNumber()).thenReturn("79876543210");
//
//        UsersContactInformation expected = UsersContactInformation.builder()
//                .id(1L).name("userName").surname("userSurname").phoneNumber(9876543210L).build();
//        //test
//        UsersContactInformation actual = usersContactInfoService.saveUserInfo(update); ???
//
//        //check
//        assertThat(actual).isEqualTo(expected);
//
////        assertThrows(NumberFormatException.class, () -> usersContactInfoService.saveUserInfo(update));
    }
}