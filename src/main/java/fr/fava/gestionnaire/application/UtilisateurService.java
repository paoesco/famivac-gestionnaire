package fr.fava.gestionnaire.application;

import fr.fava.gestionnaire.application.exceptions.WrongPasswordException;
import fr.fava.gestionnaire.domain.common.Groupe;
import fr.fava.gestionnaire.domain.common.Utilisateur;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author paoesco
 */
@Stateless
public class UtilisateurService {
    
    @Inject
    private EntityManager entityManager;
    
    public String create(AjouterUtilisateurDTO dto) {
        String password = generatePassword();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Set<Groupe> groupes = new HashSet<>(dto.getGroupes());
        Utilisateur entity = new Utilisateur(
                dto.getLogin(),
                dto.getEmail(),
                passwordEncoder.encode(password),
                groupes,
                dto.getNom(),
                dto.getPrenom());
        entityManager.persist(entity);
        return password;
    }
    
    public Utilisateur retrieve(String login) {
        Utilisateur entity = entityManager.find(Utilisateur.class, login);
        return entity;
    }
    
    public List<RetrieveUtilisateursDTO> retrieve() {
        List<Utilisateur> entities = entityManager.createNamedQuery(Utilisateur.QUERY_LISTE_ALL).getResultList();
        
        return entities.stream().map(entity -> {
            RetrieveUtilisateursDTO dto = new RetrieveUtilisateursDTO();
            dto.setLogin(entity.getLogin());
            dto.setNom(entity.getNom());
            dto.setPrenom(entity.getPrenom());
            dto.setGroupes(new ArrayList<>(entity.getGroupes()));
            dto.setEmail(entity.getEmail());
            dto.setEnabled(entity.isEnabled());
            return dto;
        }).collect(Collectors.toList());
    }
    
    public void update(Utilisateur entity) {
        entityManager.merge(entity);
    }
    
    public void delete(String login) {
        Utilisateur bean = entityManager.find(Utilisateur.class, login);
        entityManager.remove(bean);
    }
    
    public void changePassword(String login, String actualPassword, String newPassword) throws WrongPasswordException {
        Utilisateur entity = entityManager.find(Utilisateur.class, login);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(actualPassword, entity.getPassword())) {
            throw new WrongPasswordException();
        }
        entity.setPassword(passwordEncoder.encode(newPassword));
    }
    
    public String reinitPassword(String login) {
        String password = generatePassword();
        Utilisateur entity = entityManager.find(Utilisateur.class, login);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        entity.setPassword(passwordEncoder.encode(password));
        // TODO PES : envoyer email
        return password;
    }
    
    private String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
    
    public void lock(String login) {
        Utilisateur entity = entityManager.find(Utilisateur.class, login);
        entity.setEnabled(false);
        
    }
    
    public void unlock(String login) {
        Utilisateur entity = entityManager.find(Utilisateur.class, login);
        entity.setEnabled(true);
    }
    
}
