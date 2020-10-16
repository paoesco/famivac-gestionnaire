

CREATE TABLE public.chambre (
    id bigint NOT NULL,
    nombrelits integer NOT NULL,
    famille_id bigint
);



CREATE TABLE public.commune (
    code character varying(255) NOT NULL,
    ville character varying(255) NOT NULL
);



CREATE TABLE public.enfant (
    id bigint NOT NULL,
    classefrequentee character varying(255),
    datenaissance date,
    attestationcmu boolean NOT NULL,
    cartevitale boolean NOT NULL,
    contacturgence character varying(255),
    enuretique boolean NOT NULL,
    famillease boolean NOT NULL,
    telephoneurgence character varying(255),
    inscripteurestresponsablelegal boolean NOT NULL,
    nom character varying(255),
    prenom character varying(255),
    remarque character varying(2000),
    sexe character varying(255),
    inscripteur_id bigint,
    responsablelegal_id bigint
);



CREATE TABLE public.famille (
    id bigint NOT NULL,
    acceptehandicap boolean NOT NULL,
    acceptemalade boolean NOT NULL,
    ligneadressedeux character varying(255) NOT NULL,
    ligneadresseune character varying(255) NOT NULL,
    avisddcs character varying(255),
    avisrecrutement character varying(255),
    connaissanceassociation character varying(2000),
    datereceptioncasierjudiciaire date,
    daterecrutement date,
    datevisiteddcs date,
    extraitcasierjudiciaire boolean NOT NULL,
    nomrecruteur character varying(255),
    nombrefillessouhaitees integer,
    nombregarconssouhaites integer,
    precisionssejoursnoncomplets character varying(255),
    projet character varying(2000) NOT NULL,
    remarque character varying(2000),
    sejourscomplets boolean NOT NULL,
    visiteddcs boolean NOT NULL,
    commune_code character varying(255) NOT NULL
);



CREATE TABLE public.famille_chambre (
    famille_id bigint NOT NULL,
    chambres_id bigint NOT NULL
);



CREATE TABLE public.famille_periodessouhaitees (
    famille_id bigint NOT NULL,
    periodessouhaitees character varying(255)
);




CREATE TABLE public.famille_tranchesages (
    famille_id bigint NOT NULL,
    tranchesages character varying(255)
);






CREATE TABLE public.groupe (
    nom character varying(255) NOT NULL,
    libelle character varying(255)
);



CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE public.inscripteur (
    id bigint NOT NULL,
    ligneadressedeux character varying(255) NOT NULL,
    ligneadresseune character varying(255) NOT NULL,
    email character varying(255),
    fax character varying(255),
    telephone1 character varying(255),
    telephone2 character varying(255),
    nom character varying(255),
    numerosiret character varying(255),
    organisme character varying(255),
    prenom character varying(255),
    remarque character varying(2000),
    type character varying(255),
    commune_code character varying(255) NOT NULL,
    responsable_id bigint
);


CREATE TABLE public.membrefamille (
    id bigint NOT NULL,
    email character varying(255),
    fax character varying(255),
    telephone1 character varying(255),
    telephone2 character varying(255),
    datenaissance date NOT NULL,
    liendeparente character varying(255),
    nom character varying(255) NOT NULL,
    nomdenaissance character varying(255),
    prenom character varying(255) NOT NULL,
    profession character varying(255),
    referent boolean NOT NULL,
    sexe character varying(255) NOT NULL,
    communedenaissance_code character varying(255) NOT NULL,
    membres_id bigint
);



CREATE TABLE public.payeur (
    id bigint NOT NULL,
    ligneadressedeux character varying(255) NOT NULL,
    ligneadresseune character varying(255) NOT NULL,
    nom character varying(255),
    prenom character varying(255),
    type character varying(255),
    commune_code character varying(255) NOT NULL,
    sejour_id bigint
);



CREATE TABLE public.responsableinscripteur (
    id bigint NOT NULL,
    email character varying(255),
    fax character varying(255),
    telephone1 character varying(255),
    telephone2 character varying(255),
    nom character varying(255),
    prenom character varying(255)
);



CREATE TABLE public.responsablelegal (
    id bigint NOT NULL,
    ligneadressedeux character varying(255) NOT NULL,
    ligneadresseune character varying(255) NOT NULL,
    email character varying(255),
    fax character varying(255),
    telephone1 character varying(255),
    telephone2 character varying(255),
    liendeparente character varying(255),
    nom character varying(255),
    organisme character varying(255),
    prenom character varying(255),
    type character varying(255),
    commune_code character varying(255) NOT NULL
);


CREATE TABLE public.sejour (
    id bigint NOT NULL,
    datedebut date NOT NULL,
    datefin date NOT NULL,
    datefinreelle date,
    aller_id bigint,
    enfant_id bigint NOT NULL,
    famille_id bigint NOT NULL,
    retour_id bigint,
    tarif numeric(10,2)
);


CREATE TABLE public.utilisateur (
    login character varying(255) NOT NULL,
    email character varying(2000) NOT NULL,
    enabled boolean NOT NULL,
    nom character varying(255) NOT NULL,
    password character varying(2000) NOT NULL,
    prenom character varying(255) NOT NULL
);



CREATE TABLE public.utilisateur_groupe (
    utilisateurs_login character varying(255) NOT NULL,
    groupes_nom character varying(255) NOT NULL
);



CREATE TABLE public.voyage (
    id bigint NOT NULL,
    datevoyage date,
    heurearrivee time without time zone,
    heuredepart time without time zone,
    lieuarrivee character varying(255),
    lieurendezvous character varying(255),
    nompersonneareception character varying(255),
    telephonepersonneareception character varying(255),
    transport character varying(255)
);



ALTER TABLE ONLY public.chambre
    ADD CONSTRAINT chambre_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.commune
    ADD CONSTRAINT commune_pkey PRIMARY KEY (code);



ALTER TABLE ONLY public.enfant
    ADD CONSTRAINT enfant_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.famille_chambre
    ADD CONSTRAINT famille_chambre_pkey PRIMARY KEY (famille_id, chambres_id);



ALTER TABLE ONLY public.famille
    ADD CONSTRAINT famille_pkey PRIMARY KEY (id);




ALTER TABLE ONLY public.groupe
    ADD CONSTRAINT groupe_pkey PRIMARY KEY (nom);


ALTER TABLE ONLY public.inscripteur
    ADD CONSTRAINT inscripteur_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.membrefamille
    ADD CONSTRAINT membrefamille_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.payeur
    ADD CONSTRAINT payeur_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.responsableinscripteur
    ADD CONSTRAINT responsableinscripteur_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.responsablelegal
    ADD CONSTRAINT responsablelegal_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.sejour
    ADD CONSTRAINT sejour_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.famille_chambre
    ADD CONSTRAINT uk_2016dhn9lku68h1d75vy1ubco UNIQUE (chambres_id);



ALTER TABLE ONLY public.utilisateur_groupe
    ADD CONSTRAINT utilisateur_groupe_pkey PRIMARY KEY (utilisateurs_login, groupes_nom);



ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (login);



ALTER TABLE ONLY public.voyage
    ADD CONSTRAINT voyage_pkey PRIMARY KEY (id);




ALTER TABLE ONLY public.famille_chambre
    ADD CONSTRAINT fk_2016dhn9lku68h1d75vy1ubco FOREIGN KEY (chambres_id) REFERENCES public.chambre(id);



ALTER TABLE ONLY public.membrefamille
    ADD CONSTRAINT fk_2e2pegpaypxtf7vaaw42cy2cs FOREIGN KEY (communedenaissance_code) REFERENCES public.commune(code);


ALTER TABLE ONLY public.inscripteur
    ADD CONSTRAINT fk_2tevf3tiuj72veiqtg234d7b2 FOREIGN KEY (commune_code) REFERENCES public.commune(code);



ALTER TABLE ONLY public.famille_chambre
    ADD CONSTRAINT fk_3gijihmcsur0c1m7gxn5ymqk4 FOREIGN KEY (famille_id) REFERENCES public.famille(id);



ALTER TABLE ONLY public.enfant
    ADD CONSTRAINT fk_5ho4f6qcgy7eyvib7832i1ljj FOREIGN KEY (responsablelegal_id) REFERENCES public.responsablelegal(id);



ALTER TABLE ONLY public.famille
    ADD CONSTRAINT fk_6x3ukx5a2whssi5k41lhek7ul FOREIGN KEY (commune_code) REFERENCES public.commune(code);



ALTER TABLE ONLY public.utilisateur_groupe
    ADD CONSTRAINT fk_8kb3wn07t5qwb9tgtft01bl59 FOREIGN KEY (utilisateurs_login) REFERENCES public.utilisateur(login);



ALTER TABLE ONLY public.inscripteur
    ADD CONSTRAINT fk_8vm6x3nj8aprsoutkvs46wpsx FOREIGN KEY (responsable_id) REFERENCES public.responsableinscripteur(id);



ALTER TABLE ONLY public.sejour
    ADD CONSTRAINT fk_9goskutbmwqss0mle4mp5ps96 FOREIGN KEY (famille_id) REFERENCES public.famille(id);



ALTER TABLE ONLY public.payeur
    ADD CONSTRAINT fk_akxu2mgwi3latixlfsn89qhnq FOREIGN KEY (commune_code) REFERENCES public.commune(code);


ALTER TABLE ONLY public.enfant
    ADD CONSTRAINT fk_b3e453tqcl64uhpj7lw0ift2k FOREIGN KEY (inscripteur_id) REFERENCES public.inscripteur(id);



ALTER TABLE ONLY public.famille_tranchesages
    ADD CONSTRAINT fk_bs9s3m1cmy2mgkgfkcto7q6rr FOREIGN KEY (famille_id) REFERENCES public.famille(id);



ALTER TABLE ONLY public.sejour
    ADD CONSTRAINT fk_euccxh4qn2mauxq3t8gtil34r FOREIGN KEY (aller_id) REFERENCES public.voyage(id);




ALTER TABLE ONLY public.membrefamille
    ADD CONSTRAINT fk_ha65o6308qujlc14q8aqdh4jc FOREIGN KEY (membres_id) REFERENCES public.famille(id);



ALTER TABLE ONLY public.responsablelegal
    ADD CONSTRAINT fk_ho1q3r7llywciv5sdhcgavukx FOREIGN KEY (commune_code) REFERENCES public.commune(code);



ALTER TABLE ONLY public.chambre
    ADD CONSTRAINT fk_ii7teigi7o2vw9x1e6ypbvgaw FOREIGN KEY (famille_id) REFERENCES public.famille(id);



ALTER TABLE ONLY public.sejour
    ADD CONSTRAINT fk_jh4lpkravwbrqp9xoh1mdap08 FOREIGN KEY (retour_id) REFERENCES public.voyage(id);



ALTER TABLE ONLY public.famille_periodessouhaitees
    ADD CONSTRAINT fk_kr79t281hqi7gfg6jbpsw6xs5 FOREIGN KEY (famille_id) REFERENCES public.famille(id);



ALTER TABLE ONLY public.sejour
    ADD CONSTRAINT fk_lqrqvqs3ifgnx4dha7xkpt579 FOREIGN KEY (enfant_id) REFERENCES public.enfant(id);




ALTER TABLE ONLY public.payeur
    ADD CONSTRAINT fk_lseakpl4fvvr4ot7vsvbyhc58 FOREIGN KEY (sejour_id) REFERENCES public.sejour(id);




ALTER TABLE ONLY public.utilisateur_groupe
    ADD CONSTRAINT fk_t67b6nnekv5d7wym3u1j16poa FOREIGN KEY (groupes_nom) REFERENCES public.groupe(nom);


