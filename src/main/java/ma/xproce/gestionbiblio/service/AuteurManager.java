package ma.xproce.gestionbiblio.service;

import ma.xproce.gestionbiblio.dao.entities.Adherent;
import ma.xproce.gestionbiblio.dao.entities.Auteur;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuteurManager {
    public Auteur addAuteur(Auteur auteur);
    public Auteur updateAuteur(Auteur auteur);
    public  boolean deleteAuteur(Auteur auteur);
    public boolean deleteAuteurById(Integer id);
    public  Auteur getAuteurById(Integer id);
    public  Auteur getAuteurByName(String name);
    public Page<Auteur> searchAuteur(String keyword, int page, int taille);

}
