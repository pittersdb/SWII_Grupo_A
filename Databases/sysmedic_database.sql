--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: SysMedic; Type: SCHEMA; Schema: -; Owner: sysmedic
--

CREATE SCHEMA "SysMedic";


ALTER SCHEMA "SysMedic" OWNER TO sysmedic;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = "SysMedic", pg_catalog;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".users_id_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: Users; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE "Users" (
    id integer DEFAULT nextval('users_id_seq'::regclass) NOT NULL,
    nickname character varying(20) NOT NULL,
    password character varying(20) NOT NULL,
    name character varying(40) NOT NULL,
    apellidos character varying(40) NOT NULL,
    direccion character varying(100),
    telefono character varying(20),
    rol character varying(1) NOT NULL,
    enabled smallint DEFAULT 1 NOT NULL
);


ALTER TABLE "SysMedic"."Users" OWNER TO sysmedic;

--
-- Name: antecedente_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE antecedente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".antecedente_id_seq OWNER TO postgres;

--
-- Name: antecedente; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE antecedente (
    id integer DEFAULT nextval('antecedente_id_seq'::regclass) NOT NULL,
    nombre character varying(30) NOT NULL,
    descripcion character varying(500)
);


ALTER TABLE "SysMedic".antecedente OWNER TO sysmedic;

--
-- Name: cita_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE cita_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".cita_id_seq OWNER TO postgres;

--
-- Name: cita; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE cita (
    id integer DEFAULT nextval('cita_id_seq'::regclass) NOT NULL,
    fecha_generacion date NOT NULL,
    fecha_consulta_actual date NOT NULL,
    estado character varying(1) NOT NULL,
    generador_id integer NOT NULL,
    medico_id integer NOT NULL,
    paciente_id integer NOT NULL
);


ALTER TABLE "SysMedic".cita OWNER TO sysmedic;

--
-- Name: cita_cancelada_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE cita_cancelada_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".cita_cancelada_id_seq OWNER TO postgres;

--
-- Name: cita_cancelada; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE cita_cancelada (
    id integer DEFAULT nextval('cita_cancelada_id_seq'::regclass) NOT NULL,
    fecha_consulta_esperada date NOT NULL,
    fecha_cancelacion date NOT NULL,
    cita_id integer NOT NULL
);


ALTER TABLE "SysMedic".cita_cancelada OWNER TO sysmedic;

--
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".cliente_id_seq OWNER TO postgres;

--
-- Name: cliente; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE cliente (
    id integer DEFAULT nextval('cliente_id_seq'::regclass) NOT NULL,
    nombres character varying(40) NOT NULL,
    apellidos character varying(40) NOT NULL,
    ruc character varying(20) NOT NULL,
    direcion character varying(100) NOT NULL,
    telefono character varying(20) NOT NULL
);


ALTER TABLE "SysMedic".cliente OWNER TO sysmedic;

--
-- Name: consulta_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE consulta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".consulta_id_seq OWNER TO postgres;

--
-- Name: consulta; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE consulta (
    id integer DEFAULT nextval('consulta_id_seq'::regclass) NOT NULL,
    peso double precision NOT NULL,
    talla double precision NOT NULL,
    glucosa double precision NOT NULL,
    presion_arterial double precision NOT NULL,
    sintomatologia character varying(5000),
    prescripcion_medica character varying(5000),
    observaciones character varying(5000),
    cita_id integer NOT NULL,
    proxima_cita_id integer
);


ALTER TABLE "SysMedic".consulta OWNER TO sysmedic;

--
-- Name: detalle_factura_consulta_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE detalle_factura_consulta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".detalle_factura_consulta_id_seq OWNER TO postgres;

--
-- Name: detalle_factura_consulta; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE detalle_factura_consulta (
    id integer DEFAULT nextval('detalle_factura_consulta_id_seq'::regclass) NOT NULL,
    descuento integer NOT NULL,
    total double precision NOT NULL,
    tarifario_id integer NOT NULL,
    consulta_id integer NOT NULL,
    factura_id integer NOT NULL
);


ALTER TABLE "SysMedic".detalle_factura_consulta OWNER TO sysmedic;

--
-- Name: detalle_factura_medicamento_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE detalle_factura_medicamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".detalle_factura_medicamento_id_seq OWNER TO postgres;

--
-- Name: detalle_factura_medicamento; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE detalle_factura_medicamento (
    id integer DEFAULT nextval('detalle_factura_medicamento_id_seq'::regclass) NOT NULL,
    cantidad integer NOT NULL,
    precio_unitario double precision NOT NULL,
    descuento integer NOT NULL,
    precio_total double precision NOT NULL,
    factura_id integer NOT NULL,
    medicamento_lote_id integer NOT NULL
);


ALTER TABLE "SysMedic".detalle_factura_medicamento OWNER TO sysmedic;

--
-- Name: especialidad_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE especialidad_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".especialidad_id_seq OWNER TO postgres;

--
-- Name: especialidad; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE especialidad (
    id integer DEFAULT nextval('especialidad_id_seq'::regclass) NOT NULL,
    nombre character varying(100) NOT NULL
);


ALTER TABLE "SysMedic".especialidad OWNER TO sysmedic;

--
-- Name: factura_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE factura_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".factura_id_seq OWNER TO postgres;

--
-- Name: factura; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE factura (
    id integer DEFAULT nextval('factura_id_seq'::regclass) NOT NULL,
    numero integer NOT NULL,
    fecha_pago date DEFAULT '2014-07-15 22:11:47.674'::timestamp without time zone NOT NULL,
    observacion character varying(1000) NOT NULL,
    forma_pago character varying(1) NOT NULL,
    descuento_total double precision NOT NULL,
    iva double precision NOT NULL,
    total double precision NOT NULL,
    fecha_autorizacion_sri date NOT NULL,
    fecha_caducidad_sri date NOT NULL,
    cliente_id integer NOT NULL
);


ALTER TABLE "SysMedic".factura OWNER TO sysmedic;

--
-- Name: medicacion; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE medicacion (
    consulta_id integer NOT NULL,
    medicamento_id integer NOT NULL,
    instruccion character varying(1000) NOT NULL
);


ALTER TABLE "SysMedic".medicacion OWNER TO sysmedic;

--
-- Name: medicamento_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE medicamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".medicamento_id_seq OWNER TO postgres;

--
-- Name: medicamento; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE medicamento (
    id integer DEFAULT nextval('medicamento_id_seq'::regclass) NOT NULL,
    nombre character varying(40) NOT NULL,
    descripcion character varying(200)
);


ALTER TABLE "SysMedic".medicamento OWNER TO sysmedic;

--
-- Name: medicamento_inventario_operacion; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE medicamento_inventario_operacion (
    medicamento_lote_id integer NOT NULL,
    users_id integer NOT NULL,
    operacion character varying(1) NOT NULL,
    cantidad_involucrada integer NOT NULL
);


ALTER TABLE "SysMedic".medicamento_inventario_operacion OWNER TO sysmedic;

--
-- Name: medicamento_lote_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE medicamento_lote_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".medicamento_lote_id_seq OWNER TO postgres;

--
-- Name: medicamento_lote; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE medicamento_lote (
    id integer DEFAULT nextval('medicamento_lote_id_seq'::regclass) NOT NULL,
    codigo_lote bigint NOT NULL,
    fecha_elaboracion date NOT NULL,
    fecha_caducidad date NOT NULL,
    fecha_ingreso date NOT NULL,
    cantidad_disponible integer NOT NULL,
    precio double precision NOT NULL,
    estado character varying(1) NOT NULL,
    medicamento_id integer NOT NULL
);


ALTER TABLE "SysMedic".medicamento_lote OWNER TO sysmedic;

--
-- Name: medico_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE medico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".medico_id_seq OWNER TO postgres;

--
-- Name: medico; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE medico (
    id integer DEFAULT nextval('medico_id_seq'::regclass) NOT NULL,
    especialidad_id integer NOT NULL,
    users_id integer
);


ALTER TABLE "SysMedic".medico OWNER TO sysmedic;

--
-- Name: paciente_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE paciente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".paciente_id_seq OWNER TO postgres;

--
-- Name: paciente; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE paciente (
    id integer DEFAULT nextval('paciente_id_seq'::regclass) NOT NULL,
    nombres character varying(40) NOT NULL,
    ci character varying(10) NOT NULL,
    fecha_nacimiento date NOT NULL,
    sexo character varying(1) NOT NULL,
    lugar_procedencia character varying(100) NOT NULL,
    direcion character varying(100) NOT NULL,
    apellidos character varying(40)
);


ALTER TABLE "SysMedic".paciente OWNER TO sysmedic;

--
-- Name: paciente_antecedente; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE paciente_antecedente (
    paciente_id integer NOT NULL,
    antecedente_id integer NOT NULL,
    valor character varying(150) NOT NULL
);


ALTER TABLE "SysMedic".paciente_antecedente OWNER TO sysmedic;

--
-- Name: tarifario_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE tarifario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".tarifario_id_seq OWNER TO postgres;

--
-- Name: tarifario; Type: TABLE; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE TABLE tarifario (
    id integer DEFAULT nextval('tarifario_id_seq'::regclass) NOT NULL,
    nombre_servicio character varying(30) NOT NULL,
    precio double precision NOT NULL
);


ALTER TABLE "SysMedic".tarifario OWNER TO sysmedic;

--
-- Name: turno; Type: TABLE; Schema: SysMedic; Owner: postgres; Tablespace: 
--

CREATE TABLE turno (
    id integer NOT NULL,
    orden integer NOT NULL,
    cita_id integer NOT NULL
);


ALTER TABLE "SysMedic".turno OWNER TO postgres;

--
-- Name: turno_id_seq; Type: SEQUENCE; Schema: SysMedic; Owner: postgres
--

CREATE SEQUENCE turno_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "SysMedic".turno_id_seq OWNER TO postgres;

--
-- Name: turno_id_seq; Type: SEQUENCE OWNED BY; Schema: SysMedic; Owner: postgres
--

ALTER SEQUENCE turno_id_seq OWNED BY turno.id;


--
-- Name: id; Type: DEFAULT; Schema: SysMedic; Owner: postgres
--

ALTER TABLE ONLY turno ALTER COLUMN id SET DEFAULT nextval('turno_id_seq'::regclass);


--
-- Data for Name: Users; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY "Users" (id, nickname, password, name, apellidos, direccion, telefono, rol, enabled) FROM stdin;
2	gchavez	mypassword1	Gabriel	Chavez	xxxx	12345	a	1
4	kandrade	kandrade	Karina	Andreade	Calle D Avenida J	87878787878	s	1
6	fsayay	mypassword2	Fabian	Sayay	Calle h Avenida K	3434343434	m	1
3	aperez	aperez	Andres	Perez	Callex x Avenida y	1234567890	m	1
5	plucas	mypassword3	Pedro	Lucas	Calle r Avenida J	98989898989	s	1
\.


--
-- Data for Name: antecedente; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY antecedente (id, nombre, descripcion) FROM stdin;
1	tipo_sangre	\N
2	tabaquismo	\N
3	alcoholismo	\N
4	diabetes	\N
5	hipertension	\N
6	infartos	\N
7	sida	\N
8	sifilis	\N
9	hemopatias	\N
10	cardiopatias	\N
11	ateroesclerosis	\N
12	neuropatias	\N
13	artropatias	\N
14	toxicomanias	\N
15	alergias	\N
\.


--
-- Name: antecedente_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('antecedente_id_seq', 15, true);


--
-- Data for Name: cita; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY cita (id, fecha_generacion, fecha_consulta_actual, estado, generador_id, medico_id, paciente_id) FROM stdin;
3	2014-08-27	2014-08-28	d	3	1	2
2	2014-08-27	2014-08-27	d	2	1	2
1	2014-08-27	2014-08-27	e	2	1	1
\.


--
-- Data for Name: cita_cancelada; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY cita_cancelada (id, fecha_consulta_esperada, fecha_cancelacion, cita_id) FROM stdin;
\.


--
-- Name: cita_cancelada_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('cita_cancelada_id_seq', 1, false);


--
-- Name: cita_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('cita_id_seq', 3, true);


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY cliente (id, nombres, apellidos, ruc, direcion, telefono) FROM stdin;
\.


--
-- Name: cliente_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('cliente_id_seq', 1, false);


--
-- Data for Name: consulta; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY consulta (id, peso, talla, glucosa, presion_arterial, sintomatologia, prescripcion_medica, observaciones, cita_id, proxima_cita_id) FROM stdin;
1	150	1.7	120	60	Dolor en articulaciones	Tomar abundante agua	Hacer examenes de:\r\n\r\n- Prueba 1\r\n- Prueba 2	2	3
\.


--
-- Name: consulta_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('consulta_id_seq', 1, true);


--
-- Data for Name: detalle_factura_consulta; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY detalle_factura_consulta (id, descuento, total, tarifario_id, consulta_id, factura_id) FROM stdin;
\.


--
-- Name: detalle_factura_consulta_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('detalle_factura_consulta_id_seq', 1, false);


--
-- Data for Name: detalle_factura_medicamento; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY detalle_factura_medicamento (id, cantidad, precio_unitario, descuento, precio_total, factura_id, medicamento_lote_id) FROM stdin;
\.


--
-- Name: detalle_factura_medicamento_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('detalle_factura_medicamento_id_seq', 1, false);


--
-- Data for Name: especialidad; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY especialidad (id, nombre) FROM stdin;
1	General
2	Diabetologo
\.


--
-- Name: especialidad_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('especialidad_id_seq', 2, true);


--
-- Data for Name: factura; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY factura (id, numero, fecha_pago, observacion, forma_pago, descuento_total, iva, total, fecha_autorizacion_sri, fecha_caducidad_sri, cliente_id) FROM stdin;
\.


--
-- Name: factura_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('factura_id_seq', 1, false);


--
-- Data for Name: medicacion; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY medicacion (consulta_id, medicamento_id, instruccion) FROM stdin;
1	2	2 veces al dia por 3 dias
1	1	1 vez al dia por 6 dias
\.


--
-- Data for Name: medicamento; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY medicamento (id, nombre, descripcion) FROM stdin;
1	Xanax	
2	Ambix	
3	Telgerex	
\.


--
-- Name: medicamento_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('medicamento_id_seq', 3, true);


--
-- Data for Name: medicamento_inventario_operacion; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY medicamento_inventario_operacion (medicamento_lote_id, users_id, operacion, cantidad_involucrada) FROM stdin;
\.


--
-- Data for Name: medicamento_lote; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY medicamento_lote (id, codigo_lote, fecha_elaboracion, fecha_caducidad, fecha_ingreso, cantidad_disponible, precio, estado, medicamento_id) FROM stdin;
\.


--
-- Name: medicamento_lote_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('medicamento_lote_id_seq', 1, false);


--
-- Data for Name: medico; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY medico (id, especialidad_id, users_id) FROM stdin;
1	2	3
2	1	6
\.


--
-- Name: medico_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('medico_id_seq', 2, true);


--
-- Data for Name: paciente; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY paciente (id, nombres, ci, fecha_nacimiento, sexo, lugar_procedencia, direcion, apellidos) FROM stdin;
1	Maria	1111111111	1990-08-05	f	Guayaquil - Guayas	Avenida X	Loaiza
2	Esteban	2222222222	1990-08-05	m	Quito - Pichincha	Calle G	Paredes
\.


--
-- Data for Name: paciente_antecedente; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY paciente_antecedente (paciente_id, antecedente_id, valor) FROM stdin;
2	6	0
2	11	no
2	14	no
2	8	no
2	10	si
2	3	n
2	15	A los gatos
2	1	An
2	2	n
2	9	no
2	7	no
2	4	1
2	12	si
2	5	si
2	13	no
\.


--
-- Name: paciente_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('paciente_id_seq', 2, true);


--
-- Data for Name: tarifario; Type: TABLE DATA; Schema: SysMedic; Owner: sysmedic
--

COPY tarifario (id, nombre_servicio, precio) FROM stdin;
\.


--
-- Name: tarifario_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('tarifario_id_seq', 1, false);


--
-- Data for Name: turno; Type: TABLE DATA; Schema: SysMedic; Owner: postgres
--

COPY turno (id, orden, cita_id) FROM stdin;
3	1	2
4	2	1
\.


--
-- Name: turno_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('turno_id_seq', 4, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: SysMedic; Owner: postgres
--

SELECT pg_catalog.setval('users_id_seq', 6, true);


--
-- Name: antecedente_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY antecedente
    ADD CONSTRAINT antecedente_pkey PRIMARY KEY (id);


--
-- Name: cita_cancelada_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY cita_cancelada
    ADD CONSTRAINT cita_cancelada_pkey PRIMARY KEY (id);


--
-- Name: cita_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT cita_pkey PRIMARY KEY (id);


--
-- Name: cliente_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- Name: consulta_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY consulta
    ADD CONSTRAINT consulta_pkey PRIMARY KEY (id);


--
-- Name: detalle_factura_consulta_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY detalle_factura_consulta
    ADD CONSTRAINT detalle_factura_consulta_pkey PRIMARY KEY (id);


--
-- Name: detalle_factura_medicamento_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY detalle_factura_medicamento
    ADD CONSTRAINT detalle_factura_medicamento_pkey PRIMARY KEY (id);


--
-- Name: especialidad_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY especialidad
    ADD CONSTRAINT especialidad_pkey PRIMARY KEY (id);


--
-- Name: factura_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (id);


--
-- Name: medicacion_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY medicacion
    ADD CONSTRAINT medicacion_pkey PRIMARY KEY (consulta_id, medicamento_id);


--
-- Name: medicamento_inventario_operacion_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY medicamento_inventario_operacion
    ADD CONSTRAINT medicamento_inventario_operacion_pkey PRIMARY KEY (medicamento_lote_id, users_id);


--
-- Name: medicamento_lote_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY medicamento_lote
    ADD CONSTRAINT medicamento_lote_pkey PRIMARY KEY (id);


--
-- Name: medicamento_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY medicamento
    ADD CONSTRAINT medicamento_pkey PRIMARY KEY (id);


--
-- Name: medico_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY medico
    ADD CONSTRAINT medico_pkey PRIMARY KEY (id);


--
-- Name: paciente_antecedente_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY paciente_antecedente
    ADD CONSTRAINT paciente_antecedente_pkey PRIMARY KEY (antecedente_id, paciente_id);


--
-- Name: paciente_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (id);


--
-- Name: tarifario_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY tarifario
    ADD CONSTRAINT tarifario_pkey PRIMARY KEY (id);


--
-- Name: turno_id; Type: CONSTRAINT; Schema: SysMedic; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY turno
    ADD CONSTRAINT turno_id PRIMARY KEY (id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

ALTER TABLE ONLY "Users"
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: fk_cita_Users1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX "fk_cita_Users1_idx" ON cita USING btree (generador_id);


--
-- Name: fk_cita_cancelada_cita1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_cita_cancelada_cita1_idx ON cita_cancelada USING btree (cita_id);


--
-- Name: fk_cita_medico1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_cita_medico1_idx ON cita USING btree (medico_id);


--
-- Name: fk_cita_paciente1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_cita_paciente1_idx ON cita USING btree (paciente_id);


--
-- Name: fk_consulta_cita1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_consulta_cita1_idx ON consulta USING btree (cita_id);


--
-- Name: fk_consulta_cita2_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_consulta_cita2_idx ON consulta USING btree (proxima_cita_id);


--
-- Name: fk_consulta_has_medicamento_consulta1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_consulta_has_medicamento_consulta1_idx ON medicacion USING btree (consulta_id);


--
-- Name: fk_consulta_has_medicamento_medicamento1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_consulta_has_medicamento_medicamento1_idx ON medicacion USING btree (medicamento_id);


--
-- Name: fk_detalle_factura_consulta_consulta1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_detalle_factura_consulta_consulta1_idx ON detalle_factura_consulta USING btree (consulta_id);


--
-- Name: fk_detalle_factura_consulta_tarifario1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_detalle_factura_consulta_tarifario1_idx ON detalle_factura_consulta USING btree (tarifario_id);


--
-- Name: fk_detalle_factura_medicamento_factura1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_detalle_factura_medicamento_factura1_idx ON detalle_factura_medicamento USING btree (factura_id);


--
-- Name: fk_detalle_factura_medicamento_medicamento_lote1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_detalle_factura_medicamento_medicamento_lote1_idx ON detalle_factura_medicamento USING btree (medicamento_lote_id);


--
-- Name: fk_factura_cliente1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_factura_cliente1_idx ON factura USING btree (cliente_id);


--
-- Name: fk_medicamento_lote_has_Users_Users1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX "fk_medicamento_lote_has_Users_Users1_idx" ON medicamento_inventario_operacion USING btree (users_id);


--
-- Name: fk_medicamento_lote_has_Users_medicamento_lote1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX "fk_medicamento_lote_has_Users_medicamento_lote1_idx" ON medicamento_inventario_operacion USING btree (medicamento_lote_id);


--
-- Name: fk_medicamento_lote_medicamento1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_medicamento_lote_medicamento1_idx ON medicamento_lote USING btree (medicamento_id);


--
-- Name: fk_medico_especialidad1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_medico_especialidad1_idx ON medico USING btree (especialidad_id);


--
-- Name: fk_paciente_has_antecedente_antecedente1_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_paciente_has_antecedente_antecedente1_idx ON paciente_antecedente USING btree (antecedente_id);


--
-- Name: fk_paciente_has_antecedente_paciente_idx; Type: INDEX; Schema: SysMedic; Owner: sysmedic; Tablespace: 
--

CREATE INDEX fk_paciente_has_antecedente_paciente_idx ON paciente_antecedente USING btree (paciente_id);


--
-- Name: fk_cita_Users1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT "fk_cita_Users1" FOREIGN KEY (generador_id) REFERENCES "Users"(id) MATCH FULL;


--
-- Name: fk_cita_cancelada_cita1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY cita_cancelada
    ADD CONSTRAINT fk_cita_cancelada_cita1 FOREIGN KEY (cita_id) REFERENCES cita(id) MATCH FULL;


--
-- Name: fk_cita_medico1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT fk_cita_medico1 FOREIGN KEY (medico_id) REFERENCES medico(id) MATCH FULL;


--
-- Name: fk_cita_paciente1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY cita
    ADD CONSTRAINT fk_cita_paciente1 FOREIGN KEY (paciente_id) REFERENCES paciente(id) MATCH FULL;


--
-- Name: fk_consulta_cita1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY consulta
    ADD CONSTRAINT fk_consulta_cita1 FOREIGN KEY (cita_id) REFERENCES cita(id) MATCH FULL;


--
-- Name: fk_consulta_cita2; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY consulta
    ADD CONSTRAINT fk_consulta_cita2 FOREIGN KEY (proxima_cita_id) REFERENCES cita(id) MATCH FULL;


--
-- Name: fk_consulta_has_medicamento_consulta1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY medicacion
    ADD CONSTRAINT fk_consulta_has_medicamento_consulta1 FOREIGN KEY (consulta_id) REFERENCES consulta(id) MATCH FULL;


--
-- Name: fk_consulta_has_medicamento_medicamento1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY medicacion
    ADD CONSTRAINT fk_consulta_has_medicamento_medicamento1 FOREIGN KEY (medicamento_id) REFERENCES medicamento(id) MATCH FULL;


--
-- Name: fk_detalle_factura_consulta_consulta1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY detalle_factura_consulta
    ADD CONSTRAINT fk_detalle_factura_consulta_consulta1 FOREIGN KEY (consulta_id) REFERENCES consulta(id) MATCH FULL;


--
-- Name: fk_detalle_factura_consulta_factura1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY detalle_factura_consulta
    ADD CONSTRAINT fk_detalle_factura_consulta_factura1 FOREIGN KEY (factura_id) REFERENCES factura(id);


--
-- Name: fk_detalle_factura_consulta_tarifario1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY detalle_factura_consulta
    ADD CONSTRAINT fk_detalle_factura_consulta_tarifario1 FOREIGN KEY (tarifario_id) REFERENCES tarifario(id) MATCH FULL;


--
-- Name: fk_detalle_factura_medicamento_factura1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY detalle_factura_medicamento
    ADD CONSTRAINT fk_detalle_factura_medicamento_factura1 FOREIGN KEY (factura_id) REFERENCES factura(id) MATCH FULL;


--
-- Name: fk_detalle_factura_medicamento_medicamento_lote1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY detalle_factura_medicamento
    ADD CONSTRAINT fk_detalle_factura_medicamento_medicamento_lote1 FOREIGN KEY (medicamento_lote_id) REFERENCES medicamento_lote(id) MATCH FULL;


--
-- Name: fk_factura_cliente1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_factura_cliente1 FOREIGN KEY (cliente_id) REFERENCES cliente(id) MATCH FULL;


--
-- Name: fk_medicamento_lote_has_Users_Users1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY medicamento_inventario_operacion
    ADD CONSTRAINT "fk_medicamento_lote_has_Users_Users1" FOREIGN KEY (users_id) REFERENCES "Users"(id) MATCH FULL;


--
-- Name: fk_medicamento_lote_has_Users_medicamento_lote1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY medicamento_inventario_operacion
    ADD CONSTRAINT "fk_medicamento_lote_has_Users_medicamento_lote1" FOREIGN KEY (medicamento_lote_id) REFERENCES medicamento_lote(id) MATCH FULL;


--
-- Name: fk_medicamento_lote_medicamento1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY medicamento_lote
    ADD CONSTRAINT fk_medicamento_lote_medicamento1 FOREIGN KEY (medicamento_id) REFERENCES medicamento(id) MATCH FULL;


--
-- Name: fk_medico_especialidad1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY medico
    ADD CONSTRAINT fk_medico_especialidad1 FOREIGN KEY (especialidad_id) REFERENCES especialidad(id) MATCH FULL;


--
-- Name: fk_paciente_has_antecedente_antecedente1; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY paciente_antecedente
    ADD CONSTRAINT fk_paciente_has_antecedente_antecedente1 FOREIGN KEY (antecedente_id) REFERENCES antecedente(id) MATCH FULL;


--
-- Name: fk_paciente_has_antecedente_paciente; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY paciente_antecedente
    ADD CONSTRAINT fk_paciente_has_antecedente_paciente FOREIGN KEY (paciente_id) REFERENCES paciente(id) MATCH FULL;


--
-- Name: fk_turno_cita; Type: FK CONSTRAINT; Schema: SysMedic; Owner: postgres
--

ALTER TABLE ONLY turno
    ADD CONSTRAINT fk_turno_cita FOREIGN KEY (cita_id) REFERENCES cita(id);


--
-- Name: fk_users_medico; Type: FK CONSTRAINT; Schema: SysMedic; Owner: sysmedic
--

ALTER TABLE ONLY medico
    ADD CONSTRAINT fk_users_medico FOREIGN KEY (users_id) REFERENCES "Users"(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

