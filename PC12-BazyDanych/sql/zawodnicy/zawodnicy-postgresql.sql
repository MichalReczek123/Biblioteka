CREATE TABLE skocznie (
	id_skoczni integer,
	miasto text,
	kraj_s text,
	nazwa text,
	k integer,
	sedz integer
);


CREATE TABLE trenerzy (
	kraj text,
	imie_t text,
	nazwisko_t text,
	data_ur_t date
);

CREATE TABLE zawodnicy (
	id_skoczka integer,
	imie text,
	nazwisko text,
	kraj char(3),
	data_ur date,
	wzrost integer,
	waga integer
);


CREATE TABLE zawody (
	id_zawodow integer,
	id_skoczni integer,
	data date
);


INSERT INTO skocznie VALUES (1, 'Zakopane', 'POL', 'Wielka Krokiew', 120, 134);
INSERT INTO skocznie VALUES (2, 'Garmisch-Partenkirchen', 'GER', 'Wielka Skocznia Olimpijska', 115, 125);
INSERT INTO skocznie VALUES (4, 'Oberstdorf', 'GER', 'Skocznia Heiniego Klopfera', 185, 211);
INSERT INTO skocznie VALUES (3, 'Oberstdorf', 'GER', 'Grosse Schattenberg', 120, 134);
INSERT INTO skocznie VALUES (5, 'Willingen', 'GER', 'Grosse Muhlenkopfschanze', 130, 145);
INSERT INTO skocznie VALUES (6, 'Kuopio', 'FIN', 'Puijo', 120, 131);
INSERT INTO skocznie VALUES (7, 'Lahti', 'FIN', 'Salpausselka', 116, 128);
INSERT INTO skocznie VALUES (8, 'Trondheim', 'NOR', 'Granasen', 120, 132);


INSERT INTO trenerzy VALUES ('AUT', 'Alexander', 'Pointner', NULL);
INSERT INTO trenerzy VALUES ('FIN', 'Tommi', 'Nikunen', NULL);
INSERT INTO trenerzy VALUES ('NOR', 'Mika', 'Kojonkoski', '1963-04-19');
INSERT INTO trenerzy VALUES ('POL', 'Heinz', 'Kuttin', '1971-01-05');
INSERT INTO trenerzy VALUES ('GER', 'Wolfang', 'Steiert', '1963-04-19');
INSERT INTO trenerzy VALUES ('JPN', 'Hirokazu', 'Yagi', NULL);


INSERT INTO zawodnicy VALUES (1, 'Adam', 'MAŁYSZ', 'POL', '1977-12-03', 169, 60);
INSERT INTO zawodnicy VALUES (2, 'Marcin', 'BACHLEDA', 'POL', '1982-09-04', 166, 56);
INSERT INTO zawodnicy VALUES (3, 'Robert', 'MATEJA', 'POL', '1974-10-05', 180, 63);
INSERT INTO zawodnicy VALUES (4, 'Alexander', 'HERR', 'GER', '1978-10-04', 173, 65);
INSERT INTO zawodnicy VALUES (5, 'Stephan', 'HOCKE', 'GER', '1983-10-20', 178, 59);
INSERT INTO zawodnicy VALUES (6, 'Martin', 'SCHMITT', 'GER', '1978-01-29', 181, 64);
INSERT INTO zawodnicy VALUES (7, 'Michael', 'UHRMANN', 'GER', '1978-09-09', 184, 64);
INSERT INTO zawodnicy VALUES (8, 'Georg', 'SPAETH', 'GER', '1981-02-24', 187, 68);
INSERT INTO zawodnicy VALUES (9, 'Matti', 'HAUTAMAEKI', 'FIN', '1981-07-14', 174, 57);
INSERT INTO zawodnicy VALUES (10, 'Tami', 'KIURU', 'FIN', '1976-09-13', 183, 59);
INSERT INTO zawodnicy VALUES (11, 'Janne', 'AHONEN', 'FIN', '1977-05-11', 184, 67);
INSERT INTO zawodnicy VALUES (12, 'Martin', 'HOELLWARTH', 'AUT', '1974-04-13', 182, 67);
INSERT INTO zawodnicy VALUES (13, 'Thomas', 'MORGENSTERN', 'AUT', '1986-10-30', 174, 57);
INSERT INTO zawodnicy VALUES (15, 'Tommy', 'INGEBRIGTSEN', 'NOR', '1977-08-08', 179, 56);
INSERT INTO zawodnicy VALUES (16, 'Bjoern-Einar', 'ROMOEREN', 'NOR', '1981-04-01', 182, 63);
INSERT INTO zawodnicy VALUES (17, 'Roar', 'LJOEKELSOEY', 'NOR', '1976-05-31', 175, 62);
INSERT INTO zawodnicy VALUES (14, 'Alan', 'ALBORN', 'USA', '1980-12-13', 177, 57);


INSERT INTO zawody VALUES (1, 1, '2007-01-23');
INSERT INTO zawody VALUES (2, 7, '2006-11-15');
INSERT INTO zawody VALUES (3, 3, '2006-12-26');

SELECT * FROM zawodnicy;

SELECT * FROM zawodnicy FULL JOIN trenerzy USING(kraj);

