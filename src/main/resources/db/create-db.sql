--DROP TABLE users IF EXISTS;

CREATE TABLE users (
  idPokemon INTEGER PRIMARY KEY,
  name VARCHAR(30),
  email  VARCHAR(50)
);

CREATE TABLE pokemons (
  idPokemon INTEGER auto_increment PRIMARY KEY,
  name VARCHAR(30),
  weight  VARCHAR (5),
  height VARCHAR (5),
  base_experience VARCHAR (5),
  speciesUrl VARCHAR (200),
  speciesName VARCHAR (30),

);
