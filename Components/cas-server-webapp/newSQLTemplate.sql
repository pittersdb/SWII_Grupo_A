-- SI NO LES CORRE DIRECTAMENTE TODO ESTE CODIGO HAGANLO POR PARTE, CADA PARTE ESTA CON UN COMENTARIO DE TITULO CON SU NUMERO, HAGANLO EN ORDEN PORFA.

-- 1. CREATING THE BASE DE DATOS

CREATE DATABASE "SysMedic"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       CONNECTION LIMIT = -1;


-- 2. CREANDO EL ESQUEMA DE BASE DE DATOS


CREATE SCHEMA "SysMedic"
  AUTHORIZATION postgres;
  
  
  
 -- 3. CREANDO LA TABLA DE USUARIOS
 
  CREATE TABLE "SysMedic"."Users"
(
  id serial NOT NULL,
  nickname character varying(20),
  password character varying(20),
  name character varying(40),
  rol character varying(1),
  enabled smallint,
  CONSTRAINT user_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "SysMedic"."Users"
  OWNER TO postgres;
  
  
 -- 4. INSERTANDO DATOS
 
 INSERT INTO "SysMedic"."Users"(
            nickname, password, name, rol, enabled)
    VALUES ('gChavez', 'mypassword1', 'Gabriel Chavez', 'a', 1);
	
 INSERT INTO "SysMedic"."Users"(
            nickname, password, name, rol, enabled)
    VALUES ('fSayay', 'mypassword2','Fabian Sayay', 'm', 1);
 
 INSERT INTO "SysMedic"."Users"(
            nickname, password, name, rol, enabled)
    VALUES ('plucas', 'mypassword3', 'Pedro Lucas', 's', 1);
	
-- 5. CREANDO EL USUARIO DE BASE DE DATOS QUE UTILIZA EL CAS

CREATE ROLE sysmedic LOGIN
  ENCRYPTED PASSWORD 'md54b2072740ab21f2238ee837270c3b002'
  SUPERUSER INHERIT CREATEDB NOCREATEROLE REPLICATION;