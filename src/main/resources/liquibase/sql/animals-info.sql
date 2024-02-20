
CREATE TABLE IF NOT EXISTS bot.animal (
  animal_id BIGINT NOT NULL,
  name varchar(20),
  age INTEGER,
  color varchar(20),
  animal varchar(20),
  shelter_id BIGINT NOT NULL,
  PRIMARY KEY (animal_id),
  FOREIGN KEY (shelter_id) REFERENCES bot.shelters (id)
);



