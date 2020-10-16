alter table Sejour add column FAMILLE_NOM character varying(255);
alter table Sejour add column FAMILLE_PRENOM character varying(255);
alter table Sejour add column ENFANT_NOM character varying(255);
alter table Sejour add column ENFANT_PRENOM character varying(255);

update Sejour s
set FAMILLE_NOM = famille.nom,
FAMILLE_PRENOM = famille.prenom
from ( 
select m.nom, m.prenom, f.id
from MembreFamille m
inner join Famille f on f.id = m.membres_id
where m.referent = true
) famille
where famille.id = s.famille_id;

-- Vérification
--select
--s.FAMILLE_NOM, m.NOM, s.FAMILLE_PRENOM, m.PRENOM
--from Sejour s
--inner join Famille f on f.id = s.famille_id
--inner join MembreFamille m on m.membres_id = f.id
--where m.referent = true;

update Sejour s
set ENFANT_NOM = enfant.nom,
ENFANT_PRENOM = enfant.prenom
from ( 
select e.nom, e.prenom, e.id
from Enfant e
) enfant
where enfant.id = s.enfant_id;

-- Vérification
--select 
--s.ENFANT_NOM, e.NOM, s.ENFANT_PRENOM, e.PRENOM
--from Sejour s
--inner join Enfant e on e.id = s.enfant_id;

alter table Sejour alter column FAMILLE_NOM set not null;
alter table Sejour alter column FAMILLE_PRENOM set not null;
alter table Sejour alter column ENFANT_NOM set not null;
alter table Sejour alter column ENFANT_PRENOM set not null;

