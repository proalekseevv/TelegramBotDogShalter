package ru.skypro.telegrambotdogshelter.models.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ShelterInfoDto {

    private Long id;
    private String info;
    private String phoneNumber;
    private String email;
    private String infoPets;
    private String workSchedule;
    private String contactForPass;
    private String recommendationTB;

    public ShelterInfoDto(Long id, String info, String phoneNumber, String email, String infoPets, String workSchedule, String contactForPass, String recommendationTB) {
        this.id = id;
        this.info = info;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.infoPets = infoPets;
        this.workSchedule = workSchedule;
        this.contactForPass = contactForPass;
        this.recommendationTB = recommendationTB;
    }

    public ShelterInfoDto() {

    }


}