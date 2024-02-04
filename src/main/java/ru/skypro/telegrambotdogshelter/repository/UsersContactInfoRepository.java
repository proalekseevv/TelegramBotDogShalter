package ru.skypro.telegrambotdogshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.telegrambotdogshelter.models.UsersContactInformation;
@Repository
public interface UsersContactInfoRepository extends JpaRepository<UsersContactInformation,Long> {


}
