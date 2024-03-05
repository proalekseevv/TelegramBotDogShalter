package ru.skypro.telegrambotdogshelter.models.DAO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.models.Report;

@Service
public interface ReportDao {

    void save(Report report);
}

