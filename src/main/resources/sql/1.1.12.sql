alter table Sejour add column PERIODE_JOURNEE_DATE_FIN character varying(255);

UPDATE Sejour s
SET PERIODE_JOURNEE_DATE_FIN = 'APRES_MIDI'
WHERE 1=1;

alter table Sejour alter column PERIODE_JOURNEE_DATE_FIN set not null;
