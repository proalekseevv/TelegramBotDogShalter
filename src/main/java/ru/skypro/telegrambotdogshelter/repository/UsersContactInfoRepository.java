package ru.skypro.telegrambotdogshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.telegrambotdogshelter.models.UsersContactInformation;

public interface UsersContactInfoRepository extends JpaRepository<UsersContactInformation,Long> {

}
