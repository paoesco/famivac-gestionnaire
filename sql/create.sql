create table Chambre (id int8 not null, nombreLits int4 not null, famille_id int8, primary key (id))
create table Commune (code varchar(255) not null, ville varchar(255) not null, primary key (code))
create table Enfant (id int8 not null, classeFrequentee varchar(255), dateNaissance date, attestationCMU boolean not null, carteVitale boolean not null, contactUrgence varchar(255), enuretique boolean not null, familleASE boolean not null, telephoneUrgence varchar(255), inscripteurEstResponsableLegal boolean not null, nom varchar(255), prenom varchar(255), remarque varchar(2000), sexe varchar(255), familleAccueil_id int8, inscripteur_id int8, responsableLegal_id int8, primary key (id))
create table Famille (id int8 not null, accepteHandicap boolean not null, accepteMalade boolean not null, ligneAdresseDeux varchar(255) not null, ligneAdresseUne varchar(255) not null, avisDdcs varchar(255), avisRecrutement varchar(255), connaissanceAssociation varchar(2000), dateReceptionCasierJudiciaire date, dateRecrutement date, dateVisiteDdcs date, extraitCasierJudiciaire boolean not null, nomRecruteur varchar(255), nombreFillesSouhaitees int4, nombreGarconsSouhaites int4, precisionsSejoursNonComplets varchar(255), projet varchar(2000) not null, remarque varchar(2000), sejoursComplets boolean not null, visiteDdcs boolean not null, commune_code varchar(255) not null, primary key (id))
create table FamilleAccueil (id int8 not null, ligneAdresseDeux varchar(255) not null, ligneAdresseUne varchar(255) not null, email varchar(255), fax varchar(255), telephone1 varchar(255), telephone2 varchar(255), nom varchar(255), prenom varchar(255), commune_code varchar(255) not null, primary key (id))
create table Famille_Chambre (Famille_id int8 not null, chambres_id int8 not null, primary key (Famille_id, chambres_id))
create table Famille_periodesSouhaitees (Famille_id int8 not null, periodesSouhaitees varchar(255))
create table Famille_tranchesAges (Famille_id int8 not null, tranchesAges varchar(255))
create table Groupe (nom varchar(255) not null, libelle varchar(255), primary key (nom))
create table INFORMATIONS_HABITATION (id int8 not null, AUTRES_ANIMAUX varchar(1000), JARDIN_ESCPACE_JEU boolean, NOMBRE_CHATS int4, NOMBRE_CHIENS int4, PISCINE boolean, PISCINE_SECURISE boolean, POINT_VIGILANCE varchar(1000), SITUATION_HABITATION varchar(255), TYPE_HABITATION varchar(255), VILLE_LA_PLUS_PROCHE varchar(255), FAMILLE_ID int8 not null, primary key (id))
create table Inscripteur (id int8 not null, ligneAdresseDeux varchar(255) not null, ligneAdresseUne varchar(255) not null, email varchar(255), fax varchar(255), telephone1 varchar(255), telephone2 varchar(255), nom varchar(255), numeroSiret varchar(255), organisme varchar(255), prenom varchar(255), remarque varchar(2000), type varchar(255), commune_code varchar(255) not null, responsable_id int8, primary key (id))
create table MembreFamille (id int8 not null, email varchar(255), fax varchar(255), telephone1 varchar(255), telephone2 varchar(255), dateNaissance date not null, lienDeParente varchar(255), nom varchar(255) not null, nomDeNaissance varchar(255), prenom varchar(255) not null, profession varchar(255), referent boolean not null, sexe varchar(255) not null, communeDeNaissance_code varchar(255), membres_id int8, primary key (id))
create table Payeur (id int8 not null, ligneAdresseDeux varchar(255) not null, ligneAdresseUne varchar(255) not null, nom varchar(255), prenom varchar(255), type varchar(255), commune_code varchar(255) not null, sejour_id int8, primary key (id))
create table ResponsableInscripteur (id int8 not null, email varchar(255), fax varchar(255), telephone1 varchar(255), telephone2 varchar(255), nom varchar(255), prenom varchar(255), primary key (id))
create table ResponsableLegal (id int8 not null, ligneAdresseDeux varchar(255) not null, ligneAdresseUne varchar(255) not null, email varchar(255), fax varchar(255), telephone1 varchar(255), telephone2 varchar(255), lienDeParente varchar(255), nom varchar(255), organisme varchar(255), prenom varchar(255), type varchar(255), commune_code varchar(255) not null, primary key (id))
create table Sejour (id int8 not null, DATE_ANNULATION date, dateDebut date, dateFin date, dateFinReelle date, MOTIF_ANNULATION varchar(1000), MOTIF_FIN_SEJOUR varchar(1000), tarif int4 not null, aller_id int8, enfant_id int8, famille_id int8, retour_id int8, primary key (id))
create table Utilisateur (login varchar(255) not null, email varchar(2000) not null, enabled boolean not null, nom varchar(255) not null, password varchar(2000) not null, prenom varchar(255) not null, primary key (login))
create table Utilisateur_Groupe (utilisateurs_login varchar(255) not null, groupes_nom varchar(255) not null, primary key (utilisateurs_login, groupes_nom))
create table Voyage (id int8 not null, dateVoyage date, heureArrivee time, heureDepart time, lieuArrivee varchar(255), lieuRendezVous varchar(255), nomPersonneAReception varchar(255), telephonePersonneAReception varchar(255), transport varchar(255), primary key (id))
alter table Famille_Chambre add constraint UK_2016dhn9lku68h1d75vy1ubco  unique (chambres_id)
alter table Chambre add constraint FK_ii7teigi7o2vw9x1e6ypbvgaw foreign key (famille_id) references Famille
alter table Enfant add constraint FK_evct0xayck0q16x5dglh6jnar foreign key (familleAccueil_id) references FamilleAccueil
alter table Enfant add constraint FK_b3e453tqcl64uhpj7lw0ift2k foreign key (inscripteur_id) references Inscripteur
alter table Enfant add constraint FK_5ho4f6qcgy7eyvib7832i1ljj foreign key (responsableLegal_id) references ResponsableLegal
alter table Famille add constraint FK_6x3ukx5a2whssi5k41lhek7ul foreign key (commune_code) references Commune
alter table FamilleAccueil add constraint FK_qxirdjdt8v0qjodusqgfeksk1 foreign key (commune_code) references Commune
alter table Famille_Chambre add constraint FK_2016dhn9lku68h1d75vy1ubco foreign key (chambres_id) references Chambre
alter table Famille_Chambre add constraint FK_3gijihmcsur0c1m7gxn5ymqk4 foreign key (Famille_id) references Famille
alter table Famille_periodesSouhaitees add constraint FK_kr79t281hqi7gfg6jbpsw6xs5 foreign key (Famille_id) references Famille
alter table Famille_tranchesAges add constraint FK_bs9s3m1cmy2mgkgfkcto7q6rr foreign key (Famille_id) references Famille
alter table INFORMATIONS_HABITATION add constraint FK_d18dtqqiao27ubldu9mlnvylv foreign key (FAMILLE_ID) references Famille
alter table Inscripteur add constraint FK_2tevf3tiuj72veiqtg234d7b2 foreign key (commune_code) references Commune
alter table Inscripteur add constraint FK_8vm6x3nj8aprsoutkvs46wpsx foreign key (responsable_id) references ResponsableInscripteur
alter table MembreFamille add constraint FK_2e2pegpaypxtf7vaaw42cy2cs foreign key (communeDeNaissance_code) references Commune
alter table MembreFamille add constraint FK_ha65o6308qujlc14q8aqdh4jc foreign key (membres_id) references Famille
alter table Payeur add constraint FK_akxu2mgwi3latixlfsn89qhnq foreign key (commune_code) references Commune
alter table Payeur add constraint FK_lseakpl4fvvr4ot7vsvbyhc58 foreign key (sejour_id) references Sejour
alter table ResponsableLegal add constraint FK_ho1q3r7llywciv5sdhcgavukx foreign key (commune_code) references Commune
alter table Sejour add constraint FK_euccxh4qn2mauxq3t8gtil34r foreign key (aller_id) references Voyage
alter table Sejour add constraint FK_lqrqvqs3ifgnx4dha7xkpt579 foreign key (enfant_id) references Enfant
alter table Sejour add constraint FK_9goskutbmwqss0mle4mp5ps96 foreign key (famille_id) references Famille
alter table Sejour add constraint FK_jh4lpkravwbrqp9xoh1mdap08 foreign key (retour_id) references Voyage
alter table Utilisateur_Groupe add constraint FK_t67b6nnekv5d7wym3u1j16poa foreign key (groupes_nom) references Groupe
alter table Utilisateur_Groupe add constraint FK_8kb3wn07t5qwb9tgtft01bl59 foreign key (utilisateurs_login) references Utilisateur
create sequence hibernate_sequence
