package fr.famivac.gestionnaire.interfaces.web.familles;

import fr.famivac.gestionnaire.administration.control.CommuneService;
import fr.famivac.gestionnaire.familles.control.FamilleService;
import fr.famivac.gestionnaire.familles.control.MembreDTO;
import fr.famivac.gestionnaire.familles.control.MembreService;
import fr.famivac.gestionnaire.familles.entity.Chambre;
import fr.famivac.gestionnaire.familles.entity.Famille;
import fr.famivac.gestionnaire.sejours.entity.Sejour;
import fr.famivac.gestionnaire.sejours.entity.SejourRepository;
import fr.famivac.gestionnaire.interfaces.web.utils.CompleteCommune;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class FamilleDetailsBean implements Serializable, CompleteCommune {

    private Long id;

    private Famille form;

    private List<MembreDTO> membres;

    private MembreDTO selectedMembre;

    private MembreDTO membreForm;
    private boolean nouveauMembre;

    private List<Chambre> chambres;

    private Chambre nouvelleChambre;

    private List<Sejour> sejours;

    @Inject
    private SejourRepository sejourRepository;
    @Inject
    private FamilleService familleService;
    @Inject
    private MembreService membreService;
    @Inject
    private CommuneService communeService;

    /**
     * Initialisation du bean.
     */
    public void init() {
        selectedMembre = null;
        nouveauMembre = false;
        form = familleService.get(id);
        membres = new ArrayList<>();
        membreForm = new MembreDTO();
        form.getMembres().stream().map((dto) -> membreService.retrieve(dto.getId())).forEach((membre) -> {
            membres.add(membre);
        });
        membres.sort((MembreDTO m1, MembreDTO m2) -> {
            if (m1.isReferent()) {
                return -1;
            }
            if (m2.isReferent()) {
                return 1;
            }
            return m1.getId().compareTo(m2.getId());
        });
        chambres = form.getChambres();
        nouvelleChambre = new Chambre(0, form);
        sejours = sejourRepository.sejoursFamille(id);
    }

    public void update() {
        familleService.update(form);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sur la famille sauvées", ""));
    }

    public void initAjouterMembre() {
        membreForm = new MembreDTO();
        selectedMembre = null;
        nouveauMembre = true;
    }

    public void selectMembre() {
        membreForm = selectedMembre;
        nouveauMembre = false;
    }

    public void updateMembre() {
        membreService.update(membreForm);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sur le membre sauvées", ""));
    }

    public void ajouterMembre() {
        familleService.addMembre(id, membreForm);
        init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sur le membre sauvées", ""));
    }

    public void retirerMembre(MembreDTO membre) {
        familleService.removeMembre(id, membre.getId());
        init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Membre retiré", ""));
    }

    public void definirReferent(MembreDTO membre) {
        familleService.definirReferent(id, membre.getId());
        init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, MessageFormat.format("{0} {1} est désormais le membre référent de la famille", membre.getPrenom(), membre.getNom()), ""));
    }

    public void supprimerChambre(Chambre chambre) {
        familleService.deleteChambre(chambre.getId());
        init();
    }

    public void ajouterChambre() {
        familleService.addChambre(id, nouvelleChambre);
        init();
    }

    public int getNombreTotalLits() {
        int nbLits = 0;
        return chambres.stream().map((c) -> c.getNombreLits()).reduce(nbLits, Integer::sum);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Famille getForm() {
        return form;
    }

    public void setForm(Famille form) {
        this.form = form;
    }

    public FamilleService getFamilleService() {
        return familleService;
    }

    public void setFamilleService(FamilleService familleService) {
        this.familleService = familleService;
    }

    public List<MembreDTO> getMembres() {
        return membres;
    }

    public void setMembres(List<MembreDTO> membres) {
        this.membres = membres;
    }

    @Override
    public CommuneService getCommuneService() {
        return communeService;
    }

    public List<Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }

    public Chambre getNouvelleChambre() {
        return nouvelleChambre;
    }

    public void setNouvelleChambre(Chambre nouvelleChambre) {
        this.nouvelleChambre = nouvelleChambre;
    }

    public MembreDTO getSelectedMembre() {
        return selectedMembre;
    }

    public void setSelectedMembre(MembreDTO selectedMembre) {
        this.selectedMembre = selectedMembre;
    }

    public MembreDTO getMembreForm() {
        return membreForm;
    }

    public void setMembreForm(MembreDTO membreForm) {
        this.membreForm = membreForm;
    }

    public List<Sejour> getSejours() {
        return sejours;
    }

    public boolean isNouveauMembre() {
        return nouveauMembre;
    }

}
