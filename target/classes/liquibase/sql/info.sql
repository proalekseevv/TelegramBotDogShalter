--Denis
--Denis
CREATE TABLE IF NOT EXISTS bot.info
(
    id bigint,
    info text,
    number text,
    email text,
    info_pets text,
    shelter_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (shelter_id) REFERENCES bot.shelters(id)
    );