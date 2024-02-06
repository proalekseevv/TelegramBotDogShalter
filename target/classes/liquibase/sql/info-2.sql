--Konst

-- Добавление данных в таблицу bot.info

ALTER TABLE bot.info ADD work_schedule text;

UPDATE bot.info SET work_schedule = 'Режим работы:Пн: 10:00-19:00 Вт: 10:00-19:00 Ср: 10:00-19:00 Чт: 10:00-19:00 Пт: 10:00-19:00 Сб: 10:00-17:00 Вс: 10:00-17:00'
