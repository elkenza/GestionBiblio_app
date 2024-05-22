package ma.xproce.gestionbiblio.service;

import ma.xproce.gestionbiblio.dao.entities.Book;
import ma.xproce.gestionbiblio.dao.entities.Emprunt;
import ma.xproce.gestionbiblio.dao.repositories.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpruntManagerService implements EmpruntManager {
    @Autowired
    EmpruntRepository empruntRepository;
    @Override
    public Emprunt addEmprunt(Emprunt emprunt) {
        try {
            return empruntRepository.save(emprunt);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public Emprunt updateEmprunt(Emprunt emprunt) {
        Emprunt em=empruntRepository.findById(emprunt.getIdEmprunt()).get();
        em.setDateEmprunt(emprunt.getDateEmprunt());
        em.setAdherent(emprunt.getAdherent());
        em.setBook(emprunt.getBook());
        em.setDateRetour(emprunt.getDateRetour());
        return em;
    }

    @Override
    public boolean deleteEmprunt(Emprunt emprunt) {
        if(empruntRepository.existsById(emprunt.getIdEmprunt())){
            empruntRepository.delete(emprunt);
            return true;
        }else
            return false;
    }

    @Override
    public boolean deleteEmpruntById(Integer id) {
        if(empruntRepository.existsById(id)){
            empruntRepository.deleteById(id);
            return true;
        }else
          return false;
    }

    @Override
    public Emprunt getEmpruntById(Integer id) {
        return empruntRepository.findById(id).get();
    }

    @Override
    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    @Override
    public Page<Emprunt> searchBook(Integer keyword, int page, int taille) {
        return empruntRepository.findByIdEmpruntContains(keyword, PageRequest.of(page,taille));
    }
}
