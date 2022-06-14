create table juego ( id int not null AUTO_INCREMENT PRIMARY KEY, nombre varchar(50) not null, descripcion varchar(100), min_jugadores int DEFAULT 1, max_jugadores int, categoria varchar(20) );

create table partida ( id int not null AUTO_INCREMENT primary key, juego int not null, participantes int default 1, ganador varchar(20), CONSTRAINT `fk_partida_juego` FOREIGN key (juego) REFERENCES juego (id) on DELETE CASCADE on UPDATE RESTRICT );

INSERT INTO `juego` (`id`, `nombre`, `descripcion`, `min_jugadores`, `max_jugadores`, `categoria`) VALUES (NULL, 'Hold\'em Poker', 'Apuesta inteligente', '2', NULL, 'cartas'), (NULL, 'RISK', 'Conquista el mundo', '2', '6', 'mesa'), (NULL, 'Ping Pong', 'Tenis de mesa', '2', '4', 'deporte'), (NULL, 'BINGO', 'Juego de azar', '2', NULL, 'azar');

INSERT INTO `partida` (`id`, `juego`, `participantes`, `ganador`) VALUES (NULL, '1', '4', 'Rafael'), (NULL, '1', '10', 'Maria'), (NULL, '1', '8', 'Jose'), (NULL, '2', '4', 'Andres'), (NULL, '2', '4', 'Karla'), (NULL, '2', '3', 'Fernando'), (NULL, '3', '2', 'Miguel'), (NULL, '3', '2', 'Miguel'), (NULL, '3', '2', 'Raul'), (NULL, '4', '10', 'Gabriela'), (NULL, '4', '12', 'Julia'), (NULL, '4', '13', 'Patricia');