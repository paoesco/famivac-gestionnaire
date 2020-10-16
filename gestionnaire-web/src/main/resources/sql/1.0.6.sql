create table INFORMATIONS_VEHICULE (id int8 not null, ASSURANCE varchar(255), CONDUCTEUR_PRINCIPAL_VEHICULE_1 varchar(255), CONDUCTEUR_PRINCIPAL_VEHICULE_2 varchar(255), NOMBRE_PLACES_VEHICULE_1 int4, NOMBRE_PLACES_VEHICULE_2 int4, NOMBRE_REHAUSSEURS int4, NOMBRE_SIEGES_ATUO_BEBE int4, NOMBRE_VEHICULES int4, TYPE_VEHICULE_1 varchar(255), TYPE_VEHICULE_2 varchar(255), FAMILLE_ID int8 not null, primary key (id));
alter table INFORMATIONS_VEHICULE add constraint FK_e2yo1eycjsqhjy47i1tuynfje foreign key (FAMILLE_ID) references Famille;