create table FamilleAccueil (id int8 not null, ligneAdresseDeux varchar(255) not null, ligneAdresseUne varchar(255) not null, email varchar(255), fax varchar(255), telephone1 varchar(255), telephone2 varchar(255), nom varchar(255), prenom varchar(255), commune_code varchar(255) not null, primary key (id))
alter table FamilleAccueil add constraint FK_qxirdjdt8v0qjodusqgfeksk1 foreign key (commune_code) references Commune
alter table Enfant add column familleAccueil_id int8;
alter table Enfant add constraint FK_evct0xayck0q16x5dglh6jnar foreign key (familleAccueil_id) references FamilleAccueil
