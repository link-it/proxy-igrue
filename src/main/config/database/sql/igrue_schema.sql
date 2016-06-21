CREATE TABLE utenti
(
	id_amministrazione VARCHAR(255) NOT NULL,
	id_sistema INT NOT NULL,
	password VARCHAR(255) NOT NULL,
	sil VARCHAR(255),
	mef_password VARCHAR(255) NOT NULL,
	CONSTRAINT pk_utenti PRIMARY KEY (id_amministrazione,id_sistema)
);


CREATE TABLE tabellecontesto
(
	id_amministrazione VARCHAR(255) NOT NULL,
	id_sistema INT NOT NULL,
	checksum BIGINT,
	dataaggiornamento TIMESTAMP,
	nometabella VARCHAR(255) NOT NULL,
	CONSTRAINT fk_tabellecontesto_1 FOREIGN KEY (id_amministrazione,id_sistema) REFERENCES utenti(id_amministrazione,id_sistema) ON DELETE CASCADE,
	CONSTRAINT pk_tabellecontesto PRIMARY KEY (nometabella,id_amministrazione,id_sistema)
);


CREATE TABLE trasmissioni
(
	file INT NOT NULL,
	id_amministrazione VARCHAR(255) NOT NULL,
	id_sistema INT NOT NULL,
	dataultimoinvio TIMESTAMP,
	esitoultimoinvio INT,
	esitoultimoinviodescrizione VARCHAR(255),
	notificato BOOLEAN,
	CONSTRAINT fk_trasmissioni_1 FOREIGN KEY (id_amministrazione,id_sistema) REFERENCES utenti(id_amministrazione,id_sistema) ON DELETE CASCADE,
	CONSTRAINT pk_trasmissioni PRIMARY KEY (file,id_amministrazione,id_sistema)
);


CREATE TABLE tickets
(
	file INT NOT NULL,
	id_amministrazione VARCHAR(255) NOT NULL,
	id_sistema INT NOT NULL,
	dataassegnazione TIMESTAMP,
	datafinetrasmissione TIMESTAMP,
	filericevuto BOOLEAN,
	idticket VARCHAR(255) NOT NULL,
	CONSTRAINT fk_tickets_1 FOREIGN KEY (file,id_amministrazione,id_sistema) REFERENCES trasmissioni(file,id_amministrazione,id_sistema) ON DELETE CASCADE,
	CONSTRAINT pk_tickets PRIMARY KEY (file,id_amministrazione,id_sistema)
);


CREATE TABLE esiti
(
	id_amministrazione VARCHAR(255) NOT NULL,
	id_sistema INT NOT NULL,
	file INT NOT NULL,
	statisticheelaborazioni INT,
	statisticheelaborazionidescrizione VARCHAR(255),
	statistichescarti INT,
	statistichescartidescrizione VARCHAR(255),
	esitoelaborazioneperanagraficadiriferimento INT,
	esitoelaborazioneperanagraficadiriferimentodescrizione VARCHAR(255),
	logdeglierrori INT,
	logdeglierroridescrizione VARCHAR(255),
	logdeglierroriricevuto BOOLEAN,
	dataultimocontrollo TIMESTAMP,
	CONSTRAINT fk_esiti_1 FOREIGN KEY (file,id_amministrazione,id_sistema) REFERENCES trasmissioni(file,id_amministrazione,id_sistema) ON DELETE CASCADE,
	CONSTRAINT pk_esiti PRIMARY KEY (file,id_amministrazione,id_sistema)
);


CREATE TABLE eventi
(
	enddate TIMESTAMP,
	eventtype_code INT,
	eventtype_description VARCHAR(255),
	id INT NOT NULL,
	owner_description VARCHAR(255),
	owner_idamministrazione VARCHAR(255) NOT NULL,
	owner_idsistema INT NOT NULL,
	parameterid VARCHAR(255),
	reason VARCHAR(255),
	startdate TIMESTAMP,
	CONSTRAINT eventi_pkey PRIMARY KEY (id)
);


CREATE TABLE tipievento
(
	code INT NOT NULL,
	description VARCHAR(255),
	CONSTRAINT pk_tipievento PRIMARY KEY (code)
);


CREATE TABLE proprieta_eventi
(
	id VARCHAR(255) NOT NULL,
	property_key VARCHAR(255) NOT NULL,
	property_value VARCHAR(255),
	CONSTRAINT proprieta_eventi_pkey PRIMARY KEY (property_key,id)
);


CREATE TABLE mail_template
(
	template_code VARCHAR(255) NOT NULL,
	id_amministrazione VARCHAR(255) NOT NULL,
	id_sistema INT NOT NULL,
	mail_oggetto VARCHAR(255),
	template_txt VARCHAR(4000),
	last_send VARCHAR(255),
	flag_send_sn BOOLEAN,
	mail_mittente VARCHAR(255),
	mail_destinatario VARCHAR(511),
	mail_cc VARCHAR(511),
	CONSTRAINT pk_mail_template PRIMARY KEY (template_code,id_amministrazione,id_sistema)
);


CREATE TABLE mail_trace (
	template_code VARCHAR(255) NOT NULL,
	id_amministrazione VARCHAR(255) NOT NULL,
	id_sistema INT NOT NULL,
	subject VARCHAR(255) NOT NULL,
	content VARCHAR(255) NOT NULL,
	receivers VARCHAR(255) NOT NULL,
	time TIMESTAMP NOT NULL,
	CONSTRAINT fk_mail_trace_1 FOREIGN KEY (template_code,id_amministrazione,id_sistema) REFERENCES mail_template(template_code,id_amministrazione,id_sistema) ON DELETE CASCADE,
	CONSTRAINT pk_mail_trace PRIMARY KEY (template_code,id_amministrazione,id_sistema,subject,content,receivers,time)
);