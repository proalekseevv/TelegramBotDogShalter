--Elena

-- Добавление таблицы с владельцами животных


CREATE TABLE IF NOT EXISTS bot.owners (
  owners_id bigint GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name varchar(20),
  surname varchar (20),
  phone INTEGER,
  animal_name varchar(20),
  animal_type varchar(20),
  PRIMARY KEY (owners_id)
);
