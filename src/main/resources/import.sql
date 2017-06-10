DROP TABLE IF EXISTS dictionary;
CREATE TABLE dictionary (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR
);

insert into dictionary (name) values ('голубая');
insert into dictionary (name) values ('бело-голубая');
insert into dictionary (name) values ('белая');
insert into dictionary (name) values ('желто-белая');
insert into dictionary (name) values ('желтая');
insert into dictionary (name) values ('оранжевая');
insert into dictionary (name) values ('красная');

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50)
);

INSERT INTO users (name) VALUES ('Алишер');
INSERT INTO users (name) VALUES ('Евгений');
INSERT INTO users (name) VALUES ('Гульмира');

DROP TABLE IF EXISTS stars;
CREATE TABLE stars (
  id INT AUTO_INCREMENT,
  name VARCHAR(50),
  coord_x DOUBLE,
  coord_y DOUBLE,
  dict_id INT,
  user_id INT,
  FOREIGN KEY (dict_id) REFERENCES dictionary(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO stars (name, coord_x, coord_y, dict_id, user_id) VALUES ('Сириус', 43.12, 54.45, 2, 1);