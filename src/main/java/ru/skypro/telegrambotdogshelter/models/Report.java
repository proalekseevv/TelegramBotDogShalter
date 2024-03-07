package ru.skypro.telegrambotdogshelter.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "report", schema = "bot")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chatId", nullable = false)
    private Long chatId;

    @Column(name = "shelterId", nullable = false)
    private Long shelterId;

    @Column(name = "photoFileId", nullable = true)
    private String photoFileId;

    @Column(name = "creationDateTime", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss dd.MM.yyyy")
    private LocalDateTime creationDateTime; // Поле для хранения даты и времени создания отчета


    @PrePersist
    private void onCreate() {
        this.creationDateTime = LocalDateTime.now();
    }
}


