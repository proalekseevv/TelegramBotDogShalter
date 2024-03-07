CREATE TABLE bot.report (
                            id SERIAL PRIMARY KEY,
                            chatId BIGINT,
                            shelterId BIGINT,
                            photoFileId BIGINT,
                            creationDateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
