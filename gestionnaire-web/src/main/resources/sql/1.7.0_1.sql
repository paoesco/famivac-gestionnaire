
UPDATE Sejour SET datefin = datefinreelle WHERE datefinreelle IS NOT NULL;
ALTER TABLE Sejour DROP COLUMN datefinreelle;