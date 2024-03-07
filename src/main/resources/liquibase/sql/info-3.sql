ALTER TABLE bot.info ADD contact_for_pass text;
ALTER TABLE bot.info ADD recommendation_tb text;
UPDATE bot.info SET contact_for_pass = '(495) 111-11-11' WHERE id = 1;
UPDATE bot.info SET contact_for_pass = '(812) 222-22-22' WHERE id = 2;
UPDATE bot.info SET recommendation_tb = 'рекомендации о технике безопасности на территории приюта' WHERE id = 1;
UPDATE bot.info SET recommendation_tb = 'рекомендации о технике безопасности на территории приюта' WHERE id = 2;