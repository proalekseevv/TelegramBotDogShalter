--Denis
--Denis
CREATE TABLE IF NOT EXISTS bot.info
(
    id bigint,
    info text NOT NULL,
    number text NOT NULL,
    email text NOT NULL,
    info_pets text NOT NULL,
    shelter_id bigint,

    PRIMARY KEY (id),
    FOREIGN KEY (shelter_id) REFERENCES bot.shelters(id)
    );

