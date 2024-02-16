CREATE TYPE type_of_animal AS ENUM ('cat', 'dog');

CREATE TABLE IF NOT EXISTS bot.animal (
  animal_id BIGINT NOT NULL,
   name varchar(20),
   age int,
   color varchar(20),
   animal type_of_animal,
   shelter_id BIGINT NOT NULL,
   PRIMARY KEY (animal_id),
   FOREIGN KEY (shelter_id) REFERENCES bot.shelters (id)
);

