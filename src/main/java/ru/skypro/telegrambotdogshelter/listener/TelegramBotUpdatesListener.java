package ru.skypro.telegrambotdogshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.telegrambotdogshelter.botMenu.BotManagementService;
import ru.skypro.telegrambotdogshelter.services.impl.UsersContactInfoServiceImpl;
import ru.skypro.telegrambotdogshelter.services.interfaces.ShelterService;
import ru.skypro.telegrambotdogshelter.services.interfaces.UsersContactInfoService;

import javax.annotation.PostConstruct;


/**
 * Класс TelegramBotUpdatesListener является слушателем обновлений Telegram бота
 * и обрабатывает полученные обновления.
 */
@Service
@RequiredArgsConstructor
public class TelegramBotUpdatesListener {

    // Экземпляр TelegramBot для взаимодействия с ботом
    private final TelegramBot telegramBot;

    private final ShelterService shelterService;

    // Экземпляр BotManagementService для обработки обновлений и отправки сообщений
    private final BotManagementService service;


    // Логгер
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    // Экземпляр класса UsersContactInfoService для работы с данными пользователей
    private final UsersContactInfoService userService;

    /**
     * Метод, вызываемый после создания экземпляра класса.
     * Устанавливает слушателя обновлений бота.
     */
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(updates -> {
            updates.forEach(this::processUpdate);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    /**
     * Метод для обработки обновления бота.
     *
     * @param update Объект обновления бота, содержащий информацию о действиях пользователя.
     */
    private void processUpdate(Update update) {
        // Запись информации об обновлении в лог
        logger.info("Processing update: {}", update);

        // Проверка, является ли обновление результатом нажатия на кнопку встроенной клавиатуры
        if (update.callbackQuery() != null) {
            // Получение данных из callbackQuery
            String callbackData = update.callbackQuery().data();
            // Получение идентификатора чата
            Long chatId = update.callbackQuery().message().chat().id();
            // Обработка данных и идентификатора чата
            handleCallbackData(update, callbackData, chatId);
        } else if (update.message() != null && "/start".equals(update.message().text())) {
            // Обработка команды /start
            // Отправка пользователю меню с приютами
            service.sendSheltersMenu(update.message().chat().id());
            service.sendBackToSheltersButton3(update.message().chat().id());
        }
    }


    /**
     * Метод обработки данных callback и выполнения соответствующих действий.
     *
     * @param callbackData Данные callback, содержащие информацию о действии пользователя.
     * @param chatId       Идентификатор чата, где произошло действие.
     */
    private void handleCallbackData(Update update, String callbackData, Long chatId) {
        String shelterId;

        // Разделяем строку callbackData по символу '_' и берем первый элемент (индекс 0),
        // который представляет собой тип действия пользователя.
        /*
        вызов метода split для строки создает новый массив строк, но в данном случае, этот массив не сохраняется в переменной.
        Мы просто используем его результат в выражении callbackData.split("_")[0] для получения первого элемента массива.
         */
        switch (callbackData.split("_")[0]) {
            case "showShelterInfo":
                // Отображение меню с информацией о приюте
                shelterId = callbackData.replace("showShelterInfo_", "");
                service.sendShelterInfoMenu(chatId, Long.parseLong(shelterId));
                break;
            case "info":
                // Отображение текстовой информации о приюте
                shelterId = callbackData.replace("info_", "");
                service.sendShelterInfoText(chatId, Long.parseLong(shelterId));
                // Отображение кнопки "Назад"
                service.sendBackToSheltersButton2(chatId);
                break;
            case "takePet":
                // Отображение информации о том, как взять животное из приюта
                shelterId = callbackData.replace("takePet_", "");
                service.sendShelterInfoPetsText(chatId, Long.parseLong(shelterId));
                // Отображение кнопки "Назад"
                service.sendBackToSheltersButton2(chatId);
                break;
            case "backToShelters":
                // Возвращение к списку приютов
                shelterId = callbackData.replace("backToShelters", "");
                service.sendSheltersMenu4(chatId);
                break;
            case "backToShelters2":
                // Возвращение к меню приюта после дополнительного действия
                try {
                    String shelterIdStr = callbackData.substring("backToShelters".length());
                    long parsedShelterId = Long.parseLong(shelterIdStr);
                    service.sendShelterInfoMenu(chatId, parsedShelterId);
                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    // Обработка ошибок парсинга shelterId или выхода за пределы строки
                    logger.error("Error parsing shelterId from callbackData: {}", callbackData, e);
                }
                break;
            case "show":
                // Отображение меню с информацией о приюте
                shelterId = callbackData.replace("show", "");
                service.sendSheltersMenu4(chatId);
                break;

            case "sendUserInfo":
                // Вопросы о том, какую информацию нужно предоставить для связи
                shelterId = callbackData.replace("sendUserInfo_", "");
                telegramBot.execute(new SendMessage(chatId,
                        "Как к вам обращаться? Введите фамилию и имя:"));
                userService.saveUserInfo(update);

                //service.sendShelterInfoPetsText(chatId, Long.parseLong(shelterId));
                // Отображение кнопки "Назад"
                //service.sendBackToSheltersButton2(chatId);
                break;


            default:
                break;
        }
    }


    //callbackQuery() -
    //это метод в библиотеке Telegram Bot API (Pengrad Telegram Bot API), который предоставляет доступ к объекту
    // CallbackQuery в обновлении бота.
    //Объект CallbackQuery содержит информацию о пользовательском взаимодействии с кнопками встроенной клавиатуры,
    // которые часто используются для создания меню в Telegram ботах.
    //
    //Когда пользователь нажимает на кнопку с встроенной клавиатуры, бот получает CallbackQuery,
    // который содержит информацию о нажатой кнопке, чате и дополнительные данные. В вашем коде, например,
    // вы используете update.callbackQuery().data() для получения данных, связанных с действием пользователя.

    /*
    shelterId = callbackData.replace("showShelterInfo_", "");
    В данной строке кода выполняется замена подстроки "showShelterInfo_" в строке callbackData на пустую строку.
    Результат этой операции присваивается переменной shelterId.
    Таким образом, мы извлекаем идентификатор приюта из строки callbackData, удаляя префикс "showShelterInfo_".
    Этот идентификатор приюта затем используется в дальнейших операциях, например, при вызове
    метода service.sendShelterInfoMenu(chatId, Long.parseLong(shelterId))
    replace в Java используется для замены всех вхождений указанной подстроки на другую подстроку в строке
     */
}
