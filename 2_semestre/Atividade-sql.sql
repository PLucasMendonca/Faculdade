CREATE TABLE conta(
nroconta integer,
);

CREATE TABLE cliente(
cpf integer,
nome character varying(50),
codpais integer
);

CREATE TABLE contacliente
(nroconta integer,
 CPF integer
);
CREATE TABLE saldodiario(
nconta integer
diasaldo date,
valorsaldo numeric
);
CREATE TABLE pais(
codpais integer,
nomepais character varying(60)
);