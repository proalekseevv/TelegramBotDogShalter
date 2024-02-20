package ru.skypro.telegrambotdogshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.telegrambotdogshelter.models.UsersContactInformation;
@Repository
public interface UsersContactInfoRepository extends JpaRepository<UsersContactInformation,Long> {
    @Query ("SELECT phoneNumber FROM UsersContactInformation WHERE phoneNumber =:phoneNumber")
    Long existByPhoneNumber( @Param("phoneNumber") Long phoneNumber);


}
