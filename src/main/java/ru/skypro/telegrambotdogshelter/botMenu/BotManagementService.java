package ru.skypro.telegrambotdogshelter.botMenu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.exceptions.ShelterIsNotExistsException;
import ru.skypro.telegrambotdogshelter.models.DTO.ShelterDto;
import ru.skypro.telegrambotdogshelter.models.DTO.ShelterInfoDto;
import ru.skypro.telegrambotdogshelter.services.Const;
import ru.skypro.telegrambotdogshelter.services.interfaces.ShelterInfoService;
import ru.skypro.telegrambotdogshelter.services.interfaces.ShelterService;

import java.util.List;

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
    public void sendShelterInfoMenu(Long chatId, long shelterId) {
        ShelterInfoDto shelterInfo = shelterInfoService.read(shelterId);

        // Текст сообщения перед меню
        String infoON = "Выбери меню: ";

        // Создание клавиатуры с кнопками
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Узнать информацию о приюте").callbackData("info_" + shelterInfo.getId()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Как взять животное из приюта").callbackData("takePet_" + shelterInfo.getId()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Прислать отчет о питомце").callbackData("sendReport_" + shelterInfo.getId()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Расписание работы приюта, адрес и схема проезда").callbackData("workSchedule_" + shelterInfo.getId()));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Позвать волонтера").callbackData("callVolunteer"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Назад").callbackData("backToShelters"));

        // Отправка сообщения с клавиатурой
        telegramBot.execute(new SendMessage(chatId, infoON)
                .replyMarkup(inlineKeyboardMarkup));

        // Добавление кнопки "Назад" или подобной, чтобы вернуться к списку приютов
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Назад").callbackData("backToShelters"));
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



    public void processUserRequest(Long chatId, Long volunteerChatId) {

        button(chatId);

        logger.info("Отправляем пользователю ссылку на подключение к боту");

        callVolunteer(volunteerChatId);
    }


    private static Keyboard keyboardMarkup() {
        final String url = "https://t.me/+aptCEg65ORBhYzk6";
        InlineKeyboardButton button = new InlineKeyboardButton("Ссылка на чат с волонтером");
        button.url(url);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(button);
        return markup;
    }

    public void callVolunteer(Long targetChatId) {

        SendMessage request = new SendMessage(targetChatId, "Внимание! К тебе подключается пользователь");
        telegramBot.execute(request);
        logger.info("Отправка волонтеру сообщения о присоединении нового пользователя ");
    }


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
    public void sendShelterWorkScheduleText(Long chatId, long shelterId) {
        try {
            ShelterInfoDto shelterInfo = shelterInfoService.read(shelterId);

            // Формирование текста сообщения с информацией о том, расписании работы приюта, адресе и схеме проезда
            String shelterInfoMessage = "Расписание работы приюта: " + "\n" + shelterInfo.getWorkSchedule() + "\n" +
                    "Адрес: Это адрес приюта" + "\n" + "Схема проезда";
            // Отправка сообщения с информацией о приюте
            telegramBot.execute(new SendMessage(chatId, shelterInfoMessage));
            telegramBot.execute(new SendPhoto(chatId, Const.DRIVING_DIRECTION));
        } catch (ShelterIsNotExistsException e) {
            // Обработка ошибки, если приют не найден
            logger.error("Error while reading shelter with ID: {}", shelterId, e);
            telegramBot.execute(new SendMessage(chatId, "Извините, информация о приюте недоступна."));
        }
    }

}















