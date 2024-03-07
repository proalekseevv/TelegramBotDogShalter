package ru.skypro.telegrambotdogshelter.botMenu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.skypro.telegrambotdogshelter.exceptions.ShelterIsNotExistsException;
import ru.skypro.telegrambotdogshelter.models.DTO.Animal;
import ru.skypro.telegrambotdogshelter.models.DTO.ShelterDto;
import ru.skypro.telegrambotdogshelter.models.DTO.ShelterInfoDto;
import ru.skypro.telegrambotdogshelter.models.Report;
import ru.skypro.telegrambotdogshelter.repository.ReportRepository;
import ru.skypro.telegrambotdogshelter.services.Const;
import ru.skypro.telegrambotdogshelter.services.interfaces.AnimalService;
import ru.skypro.telegrambotdogshelter.services.interfaces.ShelterInfoService;
import ru.skypro.telegrambotdogshelter.services.interfaces.ShelterService;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Класс BotManagementService представляет собой сервис для управления ботом Telegram, включая отправку сообщений и формирование клавиатур.
 */
@Service
@RequiredArgsConstructor // Генерирует конструктор, внедряющий зависимости (dependency injection), для всех полей класса, которые помечены аннотацией final
public class BotManagementService {

    // Экземпляр TelegramBot для отправки сообщений
    private final TelegramBot telegramBot;

    // Экземпляр ShelterInfoService для получения информации о приюте
    private final ShelterInfoService shelterInfoService;

    private final ShelterService shelterService;
    private final AnimalService animalService;

    private final ReportRepository reportRepository;

    // Логгер
    private final Logger logger = LoggerFactory.getLogger(BotManagementService.class);

    /**
     * Метод для отправки текстового сообщения с информацией о приюте.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     * @param shelterId Идентификатор приюта, информацию о котором нужно отправить.
     */
    public void sendShelterInfoText(Long chatId, long shelterId) {
        try {
            ShelterInfoDto shelterInfo = shelterInfoService.read(shelterId);

            // Формирование текста сообщения с информацией о приюте
            String shelterInfoMessage = "Информация о приюте " + shelterInfo.getInfo() + ":\n" +
                    "Номер телефона: " + shelterInfo.getPhoneNumber() + "\n" +
                    "Email: " + shelterInfo.getEmail();

            // Отправка сообщения с информацией о приюте
            telegramBot.execute(new SendMessage(chatId, shelterInfoMessage));
        } catch (ShelterIsNotExistsException e) {
            // Обработка ошибки, если приют не найден
            logger.error("Error while reading shelter with ID: {}", shelterId, e);
            telegramBot.execute(new SendMessage(chatId, "Извините, информация о приюте недоступна."));
        }
    }

    /**
     * Метод для отправки меню с информацией о приюте.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     * @param shelterId Идентификатор приюта, информацию о котором нужно отправить.
     */
    public void sendShelterInformMenu(Long chatId, long shelterId) {
        ShelterInfoDto shelterInfo = shelterInfoService.read(shelterId);

        // Текст сообщения перед меню
        String infoON = "Выбери меню: ";

        // Создание клавиатуры с кнопками
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Узнать информацию о приюте").callbackData("info_" + shelterInfo.getId()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Как взять животное из приюта").callbackData("takePet_" + shelterInfo.getId()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Расписание работы приюта, адрес и схема проезда").callbackData("workSchedule_" + shelterInfo.getId()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Контактные данные охраны для оформления пропуска").callbackData("contactForPass_" + shelterInfo.getId()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Общие рекомендации о технике безопасности на территории приюта").callbackData("recommendationTB_" + shelterInfo.getId()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Отправить контактные данные").callbackData("sendUserInfo_" + shelterInfo.getId()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Прислать отчет о питомце").callbackData("report_" + shelterInfo.getId()));


        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Назад").callbackData("backToShelters"));
        // Отправка сообщения с клавиатурой
        telegramBot.execute(new SendMessage(chatId, infoON)
                .replyMarkup(inlineKeyboardMarkup));
//        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Назад").callbackData("backToShelters"));
    }

    /**
     * Метод для отправки меню консультации потенциального владельца животного.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendConsultationMenu(Long chatId) {
//        ShelterInfoDto shelterInfo = shelterInfoService.read(shelterId);

        // Текст сообщения перед меню
        String infoON = "Выбери меню: ";

        // Создание клавиатуры с кнопками
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Правила знакомства с животным").callbackData("introduceRules"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Список документов, необходимых для того, чтобы взять животное из приюта").callbackData("documentList" ));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Список рекомендаций по транспортировке животного").callbackData("listTransportationRecommendations"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Список рекомендаций по обустройству дома для щенка").callbackData("listRecommendationsForHomePuppy"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Список рекомендаций по обустройству дома для взрослого животного").callbackData("listRecommendationsForHomeAdultAnimal"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Список рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение, передвижение)").callbackData("listRecommendationsForHomeAnimalWithDisabilities"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Советы кинолога по первичному общению с собакой").callbackData("adviceFromDogHandler"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Список причин, почему могут отказать и не дать забрать собаку из приюта").callbackData("listReasonsForRefusal"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Прислать форму ежедневного отчета").callbackData("sendReportForm"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Рекомендации по проверенным кинологам для дальнейшего обращения к ним").callbackData("recommendationsTrustedDogHandlers"));
//
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Назад").callbackData("backToShelters"));
        // Отправка сообщения с клавиатурой
        telegramBot.execute(new SendMessage(chatId, infoON)
                .replyMarkup(inlineKeyboardMarkup));
//        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Назад").callbackData("backToShelters"));
    }

    /**
     * Метод для отправки меню для потенциального владельца животного.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendShelterInfoMenu(Long chatId, long shelterId) {
        ShelterInfoDto shelterInfo = shelterInfoService.read(shelterId);

        // Текст сообщения перед меню
        String infoON = "Выбери меню: ";

        // Создание клавиатуры с кнопками
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("О приюте").callbackData("about_" + shelterInfo.getId()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Консультация с потенциальным хозяином животного").callbackData("consultationPotentialOwnerOfShelterAnimal"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Список животных для усыновления").callbackData("listAnimals_"+shelterInfo.getId()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Отправить контактные данные").callbackData("sendUserInfo_" + shelterInfo.getId()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData("callVolunteer"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Прислать отчет о питомце").callbackData("report_" + shelterInfo.getId()));

        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Назад").callbackData("backToShelters"));

        // Отправка сообщения с клавиатурой
        telegramBot.execute(new SendMessage(chatId, infoON)
                .replyMarkup(inlineKeyboardMarkup));
    }

    /**
     * Метод для отправки текстового сообщения с информацией о том, как взять животное из приюта.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     * @param shelterId Идентификатор приюта, информацию о котором нужно отправить.
     */
    public void sendShelterInfoPetsText(Long chatId, long shelterId) {
        try {
            ShelterInfoDto shelterInfo = shelterInfoService.read(shelterId);

            // Формирование текста сообщения с информацией о том, как взять животное из приюта
            String shelterInfoMessage = "Как взять животное из приюта: " + "\n" + shelterInfo.getInfoPets();
            // Отправка сообщения с информацией о приюте
            telegramBot.execute(new SendMessage(chatId, shelterInfoMessage));
        } catch (ShelterIsNotExistsException e) {
            // Обработка ошибки, если приют не найден
            logger.error("Error while reading shelter with ID: {}", shelterId, e);
            telegramBot.execute(new SendMessage(chatId, "Извините, информация о приюте недоступна."));
        }
    }
    /**
     * Метод для отправки текстового сообщения с информацией о расписание работы приюта.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     * @param shelterId Идентификатор приюта, информацию о котором нужно отправить.
     */

    /**
     * Метод для отправки кнопки "Назад" в меню приютов (часть 1).
     *
     * @param chatId Идентификатор чата, куда отправляется сообщение.
     */
    private void sendBackToSheltersButton(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Назад").callbackData("backToShelters"));

        telegramBot.execute(new SendMessage(chatId, "Выберите приют:").replyMarkup(inlineKeyboardMarkup));
    }

    /**
     * Метод для отправки кнопки "Назад" в меню приютов (часть 2).
     *
     * @param chatId Идентификатор чата, куда отправляется сообщение.
     */
    public void sendBackToSheltersButton2(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Назад").callbackData("backToShelters2"));

        telegramBot.execute(new SendMessage(chatId, "Вернуться:").replyMarkup(inlineKeyboardMarkup));
    }

    /**
     * Метод для отправки кнопки "Назад" в меню консультации.
     *
     * @param chatId Идентификатор чата, куда отправляется сообщение.
     */
    public void sendBackToConsultationMenu(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Назад").callbackData("backToConsultationMenu"));

        telegramBot.execute(new SendMessage(chatId, "Вернуться:").replyMarkup(inlineKeyboardMarkup));
    }

    /**
     * Метод для отправки меню со списком приютов.
     *
     * @param chatId Идентификатор чата, куда отправляется сообщение.
     */
    public void sendSheltersMenu(Long chatId) {

        // Отправка приветственного сообщения
        SendResponse response2 = telegramBot.execute(new SendMessage(chatId, "Привет! Я помогаю взаимодействовать с приютами для собачек"));

    }

    public void sendSheltersMenu4(Long chatId) {
        List<ShelterDto> shelters = shelterService.getAll();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        // Создание кнопок для каждого приюта
        for (ShelterDto shelter : shelters) {
            inlineKeyboardMarkup.addRow(new InlineKeyboardButton(shelter.getName()).callbackData("showShelterInfo_" + shelter.getId()));
        }

        // Отправка сообщения со списком приютов и кнопками
        SendResponse response = telegramBot.execute(new SendMessage(chatId, "Выберите приют:")
                .replyMarkup(inlineKeyboardMarkup));

        logger.info("SendSheltersMenu response: {}", response);
    }

    public void sendBackToSheltersButton3(Long chatId) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Меню приюта").callbackData("show"));

        telegramBot.execute(new SendMessage(chatId, "Открыть:").replyMarkup(inlineKeyboardMarkup));
    }


    /** Ксения
     * Метод для отправки пользователю ссылку на чат с волонтерами и уведомление волонтеру о подключении к нему пользователя.
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     * @param volunteerChatId Идентификатор чата с волонтерами.
     */
    public void processUserRequest(Long chatId, Long volunteerChatId) {
        button(chatId);
        logger.info("Отправляем пользователю ссылку на подключение к боту");
        callVolunteer(volunteerChatId);
    }

    /** Ксения
     * Метод для отправки пользователю ссылку на чат с волонтерами без уведомления волонтера о подключении к нему пользователя.
     * @param chatId Идентификатор чата, куда отправляется сообщение.
     */
    public void processUserRequest2(Long chatId) {
        button(chatId);
        logger.info("Отправляем пользователю ссылку на подключение к чату с волонтерами");
    }

    /** Ксения
     * Метод для уведомления волонтера о подключении к нему пользователя.
     * @param targetChatId   Идентификатор чата с волонтерами.
     */

    public void callVolunteer(Long targetChatId) {
        SendMessage request = new SendMessage(targetChatId, "Внимание! К тебе подключается пользователь");
        telegramBot.execute(request);
        logger.info("Отправка волонтеру сообщения о присоединении нового пользователя ");
    }

    /** Ксения
     * Метод по реализации кнопки "Вызов волонтера".
     * @param chatId   Идентификатор чата с волонтерами.
     */
    public void button(Long chatId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Перейти в чат с волонтером").url("https://t.me/+aptCEg65ORBhYzk6")
                }
        );
        SendMessage message = new SendMessage(chatId, " Вызвать волонтера");
        message.replyMarkup(markup);
        telegramBot.execute(message);
    }

    /**
     * Метод для отправки Расписание работы приюта, адреса и схемы проезда.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendShelterWorkScheduleText(Long chatId, long shelterId) {
        try {
            ShelterInfoDto shelterInfo = shelterInfoService.read(shelterId);

            // Формирование текста сообщения с информацией о том, расписании работы приюта, адресе и схеме проезда
            String shelterInfoMessage = "Расписание работы приюта: " + "\n" + shelterInfo.getWorkSchedule() + "\n" +
                    "Адрес: Это адрес приюта." + "\n" + "Схема проезда:";
            // Отправка сообщения с информацией о приюте
            telegramBot.execute(new SendMessage(chatId, shelterInfoMessage));
            telegramBot.execute(new SendPhoto(chatId, Const.DRIVING_DIRECTION));
        } catch (ShelterIsNotExistsException e) {
            // Обработка ошибки, если приют не найден
            logger.error("Error while reading shelter with ID: {}", shelterId, e);
            telegramBot.execute(new SendMessage(chatId, "Извините, информация о приюте недоступна."));
        }

    }

    /**
     * Метод для отправки списка животных в приюте.
     * @param chatId  Идентификатор чата, куда отправляется сообщение.
     * @param shelterId  Идентификатор приюта из которого необходимо взять список животных.
     */

    public void sendListOfAnimals(Long chatId, long shelterId) {
        Collection<Animal> animals = animalService.getAnimalsByShelterId(shelterId);
        StringBuilder response = new StringBuilder();
        response.append("Список животных для усыновления:\n");
        for (Animal animal : animals) {
            response.append(animal.getTypeOfAnimal())
                    .append(" ")
                    .append(animal.getName()).append(" - ")
                    .append("полных лет ")
                    .append(animal.getAge())
                    .append(", окрас ")
                    .append(animal.getColor().toString().toLowerCase())
                    .append("\n");}
        telegramBot.execute(new SendMessage(chatId, response.toString()));
    }

    /**
     * Метод для отправки контактных данных охраны для оформления пропуска.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendShelterContactForPass(Long chatId, long shelterId) {
        try {
            ShelterInfoDto shelterInfo = shelterInfoService.read(shelterId);

            // Формирование текста сообщения с информацией о том, расписании работы приюта, адресе и схеме проезда
            String shelterInfoMessage = "Контактные данные охраны для оформления пропуска: " + shelterInfo.getContactForPass();
            // Отправка сообщения с информацией о приюте
            telegramBot.execute(new SendMessage(chatId, shelterInfoMessage));
        } catch (ShelterIsNotExistsException e) {
            // Обработка ошибки, если приют не найден
            logger.error("Error while reading shelter with ID: {}", shelterId, e);
            telegramBot.execute(new SendMessage(chatId, "Извините, информация о приюте недоступна."));
        }
    }

    /**
     * Метод для отправки общих рекомендаций о технике безопасности на территории приюта.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendShelterRecommendationTB(Long chatId, long shelterId) {
        try {
            ShelterInfoDto shelterInfo = shelterInfoService.read(shelterId);

            // Формирование текста сообщения с информацией о том, расписании работы приюта, адресе и схеме проезда
            String shelterInfoMessage = "Общие рекомендации о технике безопасности на территории приюта: " + shelterInfo.getRecommendationTB();
            // Отправка сообщения с информацией о приюте
            telegramBot.execute(new SendMessage(chatId, shelterInfoMessage));
        } catch (ShelterIsNotExistsException e) {
            // Обработка ошибки, если приют не найден
            logger.error("Error while reading shelter with ID: {}", shelterId, e);
            telegramBot.execute(new SendMessage(chatId, "Извините, информация о приюте недоступна."));
        }
    }

    /**
     * Метод для отправки правил знакомства с животным.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendIntroduceRules(Long chatId) {
            // Отправка сообщения с информацией
            telegramBot.execute(new SendMessage(chatId,
                    "Правила знакомства с животным: " + Const.INTRODUCE_RULES));
    }

    /**
     * Метод для отправки списка документов, необходимых для того, чтобы взять животное из приюта.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendDocumentList(Long chatId) {
        // Отправка сообщения с информацией
        telegramBot.execute(new SendMessage(chatId,
                "Список документов: " + Const.DOCUMENT_LIST));
    }

    /**
     * Метод для отправки списка рекомендаций по транспортировке животного.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendListTransportationRecommendations(Long chatId) {
        // Отправка сообщения с информацией
        telegramBot.execute(new SendMessage(chatId,
                "Список рекомендаций по транспортировке животного: " + Const.LIST_TRANSPORTATION_RECOMMENDATIONS));
    }

    /**
     * Метод для отправки списка рекомендаций по обустройству дома для щенка.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendListRecommendationsForHomePuppy(Long chatId) {
        // Отправка сообщения с информацией
        telegramBot.execute(new SendMessage(chatId,
                "Список рекомендаций по обустройству дома для щенка: " + Const.LIST_RECOMMENDATIONS_FOR_ARRANGING_HOME_FOR_PUPPY));
    }

    /**
     * Метод для отправки списка рекомендаций по обустройству дома для взрослого животного.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendListRecommendationsForHomeAdultAnimal(Long chatId) {
        // Отправка сообщения с информацией
        telegramBot.execute(new SendMessage(chatId,
                "Список рекомендаций по обустройству дома для взрослого животного: " + Const.LIST_RECOMMENDATIONS_FOR_ARRANGING_HOME_FOR_ADULT_ANIMAL));
    }

    /**
     * Метод для отправки списка рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение, передвижение).
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendListRecommendationsForHomeAnimalWithDisabilities(Long chatId) {
        // Отправка сообщения с информацией
        telegramBot.execute(new SendMessage(chatId,
                "Список рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение, передвижение): " + Const.LIST_RECOMMENDATIONS_FOR_ARRANGING_HOME_FOR_ANIMAL_WITH_DISABILITIES));
    }

    /**
     * Метод для отправки формы ежедневного отчета.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendReportForm(Long chatId) {
//         Отправка сообщения с информацией
        telegramBot.execute(new SendMessage(chatId,
                "форма ежедневного отчета: " ));
        telegramBot.execute(new SendMessage(chatId, Const.DAILY_REPORT_FORM));
//        telegramBot.execute(new SendDocument(chatId, Const.DAILY_REPORT_FORM));
    }

    /**
     * Метод для отправки советов кинолога по первичному общению с собакой.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendAdviceFromDogHandler(Long chatId) {
        // Отправка сообщения с информацией
        telegramBot.execute(new SendMessage(chatId,
                "Советы кинолога по первичному общению с собакой: " + Const.ADVICE_FROM_DOG_HANDLER_ON_INITIAL_COMMUNICATION_WITH_DOG));
    }

    /**
     * Метод для отправки рекомендаций по проверенным кинологам для дальнейшего обращения к ним.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendRecommendationsTrustedDogHandlers(Long chatId) {
        // Отправка сообщения с информацией
        telegramBot.execute(new SendMessage(chatId,
                "Рекомендации по проверенным кинологам для дальнейшего обращения к ним: " + Const.RECOMMENDATIONS_TRUSTED_DOG_HANDLERS));
    }

    /**
     * Метод для отправки списка причин, почему могут отказать и не дать забрать собаку из приюта.
     *
     * @param chatId    Идентификатор чата, куда отправляется сообщение.
     */
    public void sendListReasonsForRefusal(Long chatId) {
        // Отправка сообщения с информацией
        telegramBot.execute(new SendMessage(chatId,
                "Список причин, почему могут отказать и не дать забрать собаку из приюта: " + Const.LIST_REASONS_FOR_REFUSAL));
    }


    //Запасной

    public void sendReportURL(Long chatId) {

       telegramBot.execute(new SendMessage(chatId,
              "Cсылка на скачивание отчета: " + Const.REPORT_URL));
    }



    //Запасной
    public void saveUserReportInfo(Long chatId, Long shelterId) {
        Report report = new Report();
        report.setChatId(chatId);
        report.setShelterId(shelterId);
        reportRepository.save(report);
    }


    //Запасной
    private static final String BOT_TOKEN = "6701581241:AAHk1_yziH9RypjrMglo70RxDp97Af3li9I";


    //Запасной
    public void savePhotoToDatabase(String fileId, Long chatId, Long shelterId) {

            // Сохранение информации о загруженном изображении в базе данных
            Report report = new Report();
            report.setChatId(chatId);
            report.setShelterId(shelterId);
            reportRepository.save(report);
    }

    /**
     * Метод для отправки документа полученного от пользователя приюта в базу данных.
     *
     * @param chatId  Идентификатор чата, куда отправляется сообщение.
     */
    public void saveDocumentToDatabase(String fileId, Long chatId, Long shelterId) {
        // Создаем новый объект отчета
        Report report = new Report();
        report.setChatId(chatId);
        report.setShelterId(shelterId);

        // Проверяем, что fileId не null перед установкой в объект Report
        if (fileId != null) {
            report.setPhotoFileId(fileId); // Устанавливаем идентификатор файла документа
        }

        if (report.getCreationDateTime() == null) {
            report.setCreationDateTime(LocalDateTime.now());
        }

        // Сохраняем отчет в базу данных
        reportRepository.save(report);
    }












}

