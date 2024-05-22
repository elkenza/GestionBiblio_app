package ma.xproce.gestionbiblio.service;

import ma.xproce.gestionbiblio.dao.entities.Adherent;
import ma.xproce.gestionbiblio.dao.entities.Auteur;
import ma.xproce.gestionbiblio.dao.repositories.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuteurManagerService implements AuteurManager{
    @Autowired
    AuteurRepository auteurRepository;
    @Override
    public Auteur addAuteur(Auteur auteur) {
        try {
            return auteurRepository.save(auteur);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Auteur updateAuteur(Auteur auteur) {
        Auteur aut=auteurRepository.findById(auteur.getIdAuteur()).get();
        aut.setBooks(auteur.getBooks());
        aut.setName(auteur.getName());
        aut.setDateNaissance(auteur.getDateNaissance());
        auteurRepository.save(aut);
        return aut;
    }

    @Override
    public boolean deleteAuteur(Auteur auteur) {
        if(auteurRepository.existsById(auteur.getIdAuteur())){
            auteurRepository.delete(auteur);
            return true;
        }else
            return false;
    }

    @Override
    public boolean deleteAuteurById(Integer id) {
        if(auteurRepository.existsById(id)){
            auteurRepository.deleteById(id);
            return true;
        }else
          return false;
    }

    @Override
    public Auteur getAuteurById(Integer id) {
        return auteurRepository.findById(id).get();
    }

    @Override
    public Auteur getAuteurByName(String name) {
        return auteurRepository.findAuteurByNameContains(name);
    }

    @Override
    public Page<Auteur> searchAuteur(String keyword, int page, int taille) {

        return auteurRepository.findByNameContains(keyword, PageRequest.of(page,taille));
    }


}
