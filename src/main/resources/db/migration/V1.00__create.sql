DROP TABLE IF EXISTS localizacao;
DROP TABLE IF EXISTS rebels;

CREATE TABLE localizacao (
  nome_base VARCHAR(256) PRIMARY KEY,
  latitude VARCHAR(20) NOT NULL,
  longitude VARCHAR(20) NOT NULL
);

CREATE TABLE rebels (
  nome VARCHAR(256) PRIMARY KEY,
  idade int NOT NULL,
  genero VARCHAR(20) NOT NULL,
  nome_base VARCHAR(256) NOT NULL,
  arma int NOT NULL,
  municao int NOT NULL,
  agua int NOT NULL,
  comida int NOT NULL,
  traidor int NOT NULL
);

