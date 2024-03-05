package ru.skypro.telegrambotdogshelter.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.telegrambotdogshelter.models.DTO.UsersDto;
import ru.skypro.telegrambotdogshelter.models.Users;

@Component
public class UsersMapper {

    public UsersDto toUsersContactInformationDto(Users users) {
      UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setName(users.getName());
        usersDto.setSurname(users.getSurname());
  //      usersDto.setEmail(users.getEmail());
     //   usersDto.setAge(users.getAge());
        usersDto.setPhoneNumber(users.getPhoneNumber());
        usersDto.setChatId(users.getChatId());
        return usersDto;
    }
    public Users toUsersContactInformation(UsersDto usersDto) {
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setName(usersDto.getName());
        users.setSurname(usersDto.getSurname());
   //     users.setEmail(usersDto.getEmail());
   //     users.setAge(usersDto.getAge());
        users.setPhoneNumber(usersDto.getPhoneNumber());
        users.setChatId(usersDto.getChatId());
        return users;
    }
}
