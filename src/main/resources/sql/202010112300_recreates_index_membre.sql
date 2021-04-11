DROP INDEX idx_membrefamille_nom;
DROP INDEX idx_membrefamille_prenom;

CREATE INDEX idx_membrefamille_lower_nom ON membrefamille (lower(nom));
CREATE INDEX idx_membrefamille_lower_prenom ON membrefamille (lower(prenom));