INSERT INTO utenti(id_amministrazione, id_sistema, password, mef_password) VALUES('#IDAMMINISTRAZIONE#', #IDSISTEMA#, '#PASSWORD#', '#MEFPASSWORD#'); 


INSERT INTO mail_template VALUES ('DAT_CNT_01', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di download dei dati di contesto ''DAT_CNT_01'' Sender', 'Errore durante la fase di download dei dati di contesto.
Il download dei dati di contesto è stata terminata in modo non corretto.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('DAT_CNT_04', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di download dei dati di contesto ''DAT_CONT_04'' Sender', 'Warning durante la fase di download dei dati di contesto.
Il download dei dati di contesto verrà rischedulata in quanto il Sistema Informativo Igrue è momentaneamente non disponibile.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('GST_EVN_03', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di cancellazione degli eventi ''GST_EVN_03'' Sender', 'Errore durante la fase di cancellazione degli eventi pubblicati dal Sistema Informativo Igrue.
La fase di cancellazione degli eventi è stata terminata in modo non corretto.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('GST_EVN_01', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di acquisizione degli eventi ''GST_EVN_01'' Sender', 'Errore durante la fase di acquisizione degli eventi pubblicati dal Sistema Informativo Igrue.
La fase di acquisizione degli eventi è stata terminata in modo non corretto.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('DAT_CNT_03', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di download dei dati di contesto ''DAT_CNT_03'' Sender', 'Errore durante la fase di download dei dati di contesto.
Il download dei dati di contesto per è stata terminata in modo non corretto a causa di problemi legati al salvataggio del dato di contesto su File System.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('SCR_RIS_04', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di download esito elaborazione ''SRC_RIS_04'' Sender', 'Errore durante la fase di download esito elaborazione dal Sistema Informativo Igrue.
Il download esito elaborazione relativo alla richiesta di trasmissione dei dati di protocollo è stata terminata in modo non corretto.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('SCR_RIS_02', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di download esito elaborazione ''SRC_RIS_02'' Sender', 'Errore durante la fase di download esito elaborazione dal Sistema Informativo Igrue.
Il download esito elaborazione relativo alla richiesta di trasmissione dei dati di protocollo è stata terminata a seguito della impossibilità di comunicare con il Sistema Informativo Igrue.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('INV_FIL_05', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di trasmissione dati ''INV_FIL_05'' Sender', 'Errore durante la fase di trasmissione dati al Sistema Informativo Igrue.
La richiesta di trasmissione dei dati di protocollo è stata terminata in modo non corretto.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('GST_EVN_05', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di acquisizione degli eventi ''GST_EVN_05'' Sender', 'Warning durante la fase di acquisizione degli eventi pubblicati dal Sistema Informativo Igrue.
La fase di acquisizione degli eventi verrà rischedulata in quanto il Sistema Informativo Igrue è momentaneamente non disponibile.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('INV_FIL_04', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di trasmissione dati ''INV_FIL_04'' Sender', 'Errore durante la fase di trasmissione dati al Sistema Informativo Igrue.
La richiesta di trasmissione dei dati di protocollo è stata terminata a causa della mancata ricezione di conferma della corretta acquisizione del tracciato record da parte del Sistema Informativo Igrue secondo i termini previsti dalla configurazione.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('INV_FIL_03', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di trasmissione dati ''INV_FIL_03'' Sender', 'Errore durante la fase di trasmissione dati al Sistema Informativo Igrue.
La richiesta di trasmissione dei dati di protocollo è stata terminata in modo non corretto a causa dell''impossibilita di processare la richiesta.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.
', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('INV_FIL_02', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di trasmissione dati ''INV_FIL_02'' Sender', 'Errore durante la fase di trasmissione dati al Sistema Informativo Igrue.
La richiesta di trasmissione dei dati di protocollo è stata terminata a seguito della impossibilità di comunicare il Sistema Informativo Igrue.

Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('INV_FIL_01', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di trasmissione dati ''INV_FIL_01'' Sender', 'Warning durante la fase di trasmissione dati al Sistema Informativo Igrue.
La richiesta di trasmissione dei dati di protocollo verrà rieseguita a causa della momentanea impossibilità di comunicare  con il Sistema Informativo Igrue 
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('GST_EVN_04', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di cancellazione degli eventi ''GST_EVN_04'' Sender', 'Errore durante la fase di cancellazione degli eventi pubblicati dal Sistema Informativo Igrue.
La fase di cancellazione degli eventi è stata terminata in modo non corretto.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.
', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('SCR_RIS_01', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di download esito elaborazione  ''SCR_RIS_01'' Sender', 'Warning durante la fase di download esito elaborazione dal Sistema Informativo Igrue.
Il download esito elaborazione relativo alla richiesta di trasmissione dei dati di protocollo verrà rieseguito a causa della momentanea impossibilità di comunicare con il Sistema Informativo Igrue.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('DAT_CNT_05', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di download dei dati di contesto ''DAT_CONT_05'' Sender', 'Warning durante la fase di download dei dati di contesto.
Il download dei dati di contesto verrà rischedulata in quanto il Sistema Informativo Igrue è momentaneamente non disponibile.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('INV_FIL_06', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di trasmissione dati ''INV_FIL_06'' Sender', 'Errore durante la fase di trasmissione dati al Sistema Informativo Igrue.
La richiesta di trasmissione dei dati di protocollo è stata terminata in modo non corretto.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('SCR_RIS_05', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di download esito elaborazione ''SRC_RIS_05'' Sender', 'Errore durante la fase di download esito elaborazione dal Sistema Informativo Igrue.
Il download esito elaborazione relativo alla richiesta di trasmissione dei dati di protocollo è stata terminata in modo non corretto.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('GST_EVN_08', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di cancellazione degli eventi ''GST_EVN_08'' Sender', 'Warning durante la fase di cancellazione degli eventi Sistema Informativo Igrue.
La fase di cancellazione degli eventi verrà rischedulata in quanto il Sistema Informativo Igrue è momentaneamente non disponibile.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('GST_EVN_07', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di acquisizione degli eventi ''GST_EVN_07'' Sender', 'Warning durante la fase di acquisizione degli eventi pubblicati dal Sistema Informativo Igrue.
La fase di acquisizione degli eventi verrà rischedulata in quanto il Sistema Informativo Igrue è momentaneamente non disponibile.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('SCR_RIS_03', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di download esito elaborazione ''SRC_RIS_03'' Sender', 'Errore durante la fase di download esito elaborazione dal Sistema Informativo Igrue.
Il download esito elaborazione relativo alla richiesta di trasmissione dei dati di protocollo è stata terminata in modo non corretto a causa della impossibilità di salvare correttamente su file system il risultato.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('DAT_CNT_02', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase  di download dei dati di contesto ''DAT_CNT_02'' Sender', 'Errore durante la fase di download dei dati di contesto.
Il download dei dati di contesto è stata terminata in modo non corretto.

Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('INV_FIL_07', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di trasmissione dati ''INV_FIL_07'' Sender', 'Warning durante la fase di trasmissione dati al Sistema Informativo Igrue.
La richiesta di trasmissione dei dati di protocollo verrà rischedulata in quanto il Sistema Informativo Igrue ha segnalato un errore.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('GST_EVN_06', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di cancellazione degli eventi ''GST_EVN_06'' Sender', 'Warning durante la fase di cancellazione degli eventi Sistema Informativo Igrue.
La fase di cancellazione degli eventi verrà rischedulata in quanto il Sistema Informativo Igrue è momentaneamente non disponibile.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('SCR_RIS_06', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di download esito elaborazione ''SRC_RIS_06'' Sender', 'Warning durante la fase di download esito elaborazione dal Sistema Informativo Igrue.
Il download esito elaborazione relativo alla richiesta di trasmissione dei dati di protocollo verrà rischedulata in quanto il Sistema Informativo Igrue ha segnalato un errore.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('RIC_TCK_01', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di acquisizione di un ticket.', 'Warning durante la fase di acquisizione di un ticket.
La fase di contrattazione di un nuovo ticket verrà rischedulata in quanto il Sistema Informativo Igrue è momentaneamente non disponibile.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('RIC_TCK_02', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di acquisizione di un ticket.', 'Warning durante la fase di acquisizione di un ticket.
La contrattazione di un ticket è stata interrotta a causa della momentanea impossibilità di comunicare con il database.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('RIC_TCK_03', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di acquisizione di un ticket.', 'Warning durante la fase di acquisizione di un ticket.
Il Sistema Informativo Igrue ha restituito un esito negativo alla richiesta di un nuovo ticket.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('RIC_TCK_04', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di acquisizione di un ticket.', 'Warning durante la fase di acquisizione di un ticket.
La contrattazione di un nuovo ticket è terminata con errore.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('INV_FIL_08', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Warning durante la fase di trasmissione dati', 'Warning durante la fase di trasmissione dati al Sistema Informativo Igrue.
Il Sistema Informativo Igrue ha restituito un esito negativo a seguito di una trasmissione di dati.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('GST_EVN_02', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore input/output durante la fase di acquisizione degli eventi', 'Errore input/output durante la fase di acquisizione degli eventi.
Si sono verificati errori a seguito di operazioni input/output su filesystem durante la fase di acquisizione degli eventi.
Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('INV_FIL_09', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Dati di attuazione inviati', 'Dati di attuazione correttamente inviati al Sistema Informativo Igrue.
Il Sistema Informativo Igrue ha restituito un esito positivo a seguito di una trasmissione di dati.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('INV_FIL_10', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Dati di attuazione gia inviati', 'Dati di attuazione correttamente inviati al Sistema Informativo Igrue.
Il Sistema Informativo Igrue ha restituito un esito positivo a seguito di una trasmissione di dati.
I dati risultavano gia consegnati precedentemente.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('RIC_EVN_01', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Ricevuto evento - File ricevuto', 'Il Sistema Informativo Igrue ha confermato la ricezione dei dati.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('RIC_EVN_02', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Ricevuto evento - File scartato', 'Il Sistema Informativo Igrue ha scartato i dati inviati.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('RIC_EVN_03', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Ricevuto evento - Elaborazione con successo', 'Il Sistema Informativo Igrue ha elaborato con successo i dati trasmessi.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('RIC_EVN_04', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Ricevuto evento - Elaborazione con errore', 'Il Sistema Informativo Igrue ha terminato l elaborazione dei dati con errore.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('RIC_EVN_06', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Ricevuto evento - Elaborazione respinta', 'Il Sistema Informativo Igrue ha respinto l elaborazione dei dati.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('RIC_EVN_05', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Ricevuto evento - Elaborazione con scarto totale', 'Il Sistema Informativo Igrue ha terminato l elaborazione dei dati con scarto totale.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('RIC_EVN_07', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Ricevuto evento - Errore invio', 'Il Sistema Informativo Igrue ha riscontrato un errore nell invio dei dati.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('RIC_EVN_08', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Ricevuto evento - Errore scarico risultato', 'Il Sistema Informativo Igrue ha riscontrato un errore di scarico risultato.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('RIC_EVN_09', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Ricevuto evento - Delta tipologiche', '', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('STR_SYS_01', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di avvio del sistema ''STR_SYS_01'' Sender', 'Errore durante la fase di avvio del sistema.
Si sono verificati dei problemi durante il caricamento della configurazione.
Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('STR_SYS_02', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Errore durante la fase di avvio del sistema ''STR_SYS_02'' Sender', 'Errore durante la fase di avvio del sistema.
Si sono verificati dei problemi di connessione con il databale.
Far pervenire ai gruppi di assistenza tecnica in allegato questa mail.
Grazie.', '1970-01-01 00:00:00', true);
INSERT INTO mail_template VALUES ('DAT_CNT_06', '#IDAMMINISTRAZIONE#', #IDSISTEMA#,'Aggiornamento delle tabelle di contesto.', 'Il download dei dati di contesto è stata terminata in modo corretto.
', '1970-01-01 00:00:00', true);


UPDATE mail_template SET flag_send_sn = 'f' WHERE template_code = 'DAT_CNT_02';
UPDATE mail_template SET flag_send_sn = 'f' WHERE template_code = 'GST_EVN_04';
--
-- Data for Name: proprieta_eventi; Type: TABLE DATA; Schema: public; Owner: igrue
--



--
-- Data for Name: tabellecontesto; Type: TABLE DATA; Schema: public; Owner: igrue
--

INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'Complessivo');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T0');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T1');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T2');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T3');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T4');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T5');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T6');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T7');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T8');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T9');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T10');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T11');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T12');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T13');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T14');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T15');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T16');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T17');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T18');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T19');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T20');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T21');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T22');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T23');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T24');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T25');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T26');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T27');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T28');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T29');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T30');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T31');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T32');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T33');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T34');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T35');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T36');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T37');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T38');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T39');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T40');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T41');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T42');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T43');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T44');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T45');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T46');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T47');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T48');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T49');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T50');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T51');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T52');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T53');
INSERT INTO tabellecontesto VALUES ('#IDAMMINISTRAZIONE#', #IDSISTEMA#, NULL, '1970-01-01 00:00:00', 'T54');


