package ru.skypro.telegrambotdogshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.telegrambotdogshelter.models.Report;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {


    default void saveReport(Report report) {
        save(report);
    }
}
