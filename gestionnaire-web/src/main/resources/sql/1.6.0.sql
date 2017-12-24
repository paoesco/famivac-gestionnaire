
ALTER TABLE Sejour DROP tarif;
ALTER TABLE Sejour ADD COLUMN FRAIS_SEJOUR_JOURNALIER NUMERIC(10, 2);

ALTER TABLE Tarif RENAME TO FRAIS_SEJOUR_JOURNALIER;

UPDATE Sejour SET FRAIS_SEJOUR_JOURNALIER = 0 WHERE FRAIS_SEJOUR_JOURNALIER IS NULL;