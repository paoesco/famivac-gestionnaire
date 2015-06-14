alter table Chambre drop constraint FK_ii7teigi7o2vw9x1e6ypbvgaw
alter table Enfant drop constraint FK_b3e453tqcl64uhpj7lw0ift2k
alter table Enfant drop constraint FK_5ho4f6qcgy7eyvib7832i1ljj
alter table Famille drop constraint FK_6x3ukx5a2whssi5k41lhek7ul
alter table Famille_Chambre drop constraint FK_2016dhn9lku68h1d75vy1ubco
alter table Famille_Chambre drop constraint FK_3gijihmcsur0c1m7gxn5ymqk4
alter table Famille_MembreFamille drop constraint FK_ogu8cgcdhaxhm9hxf7g568w71
alter table Famille_MembreFamille drop constraint FK_npdphmkpddx3m3r9bkd3naew2
alter table Famille_periodesSouhaitees drop constraint FK_kr79t281hqi7gfg6jbpsw6xs5
alter table Famille_tranchesAges drop constraint FK_bs9s3m1cmy2mgkgfkcto7q6rr
alter table Inscripteur drop constraint FK_2tevf3tiuj72veiqtg234d7b2
alter table Inscripteur drop constraint FK_8vm6x3nj8aprsoutkvs46wpsx
alter table MembreFamille drop constraint FK_2e2pegpaypxtf7vaaw42cy2cs
alter table MembreFamille drop constraint FK_1rweiuqvpe4ykus7jgrlqwgow
alter table Payeur drop constraint FK_akxu2mgwi3latixlfsn89qhnq
alter table Payeur drop constraint FK_lseakpl4fvvr4ot7vsvbyhc58
alter table ResponsableLegal drop constraint FK_ho1q3r7llywciv5sdhcgavukx
alter table Sejour drop constraint FK_euccxh4qn2mauxq3t8gtil34r
alter table Sejour drop constraint FK_lqrqvqs3ifgnx4dha7xkpt579
alter table Sejour drop constraint FK_9goskutbmwqss0mle4mp5ps96
alter table Sejour drop constraint FK_jh4lpkravwbrqp9xoh1mdap08
alter table Utilisateur_Groupe drop constraint FK_t67b6nnekv5d7wym3u1j16poa
alter table Utilisateur_Groupe drop constraint FK_8kb3wn07t5qwb9tgtft01bl59
drop table Chambre if exists
drop table Commune if exists
drop table Enfant if exists
drop table Famille if exists
drop table Famille_Chambre if exists
drop table Famille_MembreFamille if exists
drop table Famille_periodesSouhaitees if exists
drop table Famille_tranchesAges if exists
drop table Groupe if exists
drop table Inscripteur if exists
drop table MembreFamille if exists
drop table Payeur if exists
drop table ResponsableInscripteur if exists
drop table ResponsableLegal if exists
drop table Sejour if exists
drop table Utilisateur if exists
drop table Utilisateur_Groupe if exists
drop table Voyage if exists
