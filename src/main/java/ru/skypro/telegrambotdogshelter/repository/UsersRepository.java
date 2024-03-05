package ru.skypro.telegrambotdogshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.telegrambotdogshelter.models.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    @Query ("SELECT phoneNumber FROM Users WHERE phoneNumber =:phoneNumber")
    Long existByPhoneNumber( @Param("phoneNumber") Long phoneNumber);


}
