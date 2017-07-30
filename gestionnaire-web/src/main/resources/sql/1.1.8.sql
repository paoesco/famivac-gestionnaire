alter table Sejour add column PERIODE_JOURNEE_DATE_DEBUT character varying(255);

UPDATE Sejour s
SET PERIODE_JOURNEE_DATE_DEBUT = 'MATIN'
WHERE 1=1;

alter table Sejour alter column PERIODE_JOURNEE_DATE_DEBUT set not null;

