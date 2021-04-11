alter table Famille add column DATE_RADIATION date;
ALTER TABLE Famille ADD COLUMN CANDIDATURE boolean NOT NULL default false;


ALTER TABLE Famille ALTER COLUMN projet DROP NOT NULL;
ALTER TABLE Famille ALTER COLUMN ligneAdresseUne DROP NOT NULL;
ALTER TABLE Famille ALTER COLUMN ligneAdresseDeux DROP NOT NULL;
ALTER TABLE Famille ALTER COLUMN commune_code DROP NOT NULL;

ALTER TABLE MembreFamille ALTER COLUMN dateNaissance DROP NOT NULL;