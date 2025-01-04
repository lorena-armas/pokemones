CREATE USER 'pokemon_user'@'%' IDENTIFIED BY 'qwerty';
GRANT ALL PRIVILEGES ON *.* TO 'pokemon_user'@'%' WITH GRANT OPTION;
CREATE schema pokemones_db;

USE pokemones_db;

CREATE TABLE IF NOT EXISTS pokemones (
    code bigint auto_increment,
    name varchar(25) NOT NULL,
    type varchar(25) NOT NULL,

    PRIMARY KEY (code)
);


INSERT INTO pokemones (name, type) VALUES ('Pikachu', 'Eléctrico');
INSERT INTO pokemones (name, type) VALUES ('Charmander', 'Fuego');
INSERT INTO pokemones (name, type) VALUES ('Squirtle', 'Agua');

