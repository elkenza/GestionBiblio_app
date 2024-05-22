package ma.xproce.gestionbiblio.service;

import ma.xproce.gestionbiblio.dao.entities.Adherent;
import ma.xproce.gestionbiblio.dao.repositories.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdherentMangerService implements AdherentManager{
    @Autowired
    AdherentRepository adherentRepository;
    @Override
    public Adherent addAdherent(Adherent adherent) {
        try {
           return adherentRepository.save(adherent);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Adherent updateAdherent(Adherent adherent) {
        Adherent ad=adherentRepository.findById(adherent.getIdAd()).get();
        ad.setAbonnements(adherent.getAbonnements());
        ad.setEmprunts(adherent.getEmprunts());
        ad.setTele(adherent.getTele());
        ad.setName(adherent.getName());
        ad.setLivres(adherent.getLivres());
        adherentRepository.save(ad);
        return ad;
    }

    @Override
    public boolean deleteAdherent(Adherent adherent) {
       if (adherentRepository.existsById(adherent.getIdAd())){
           adherentRepository.delete(adherent);
           return true;
       }else
           return false;
    }

    @Override
    public boolean deleteAdherentById(Integer id) {
        if (adherentRepository.existsById(id)){
            adherentRepository.deleteById(id);
            return true;
        }else
         return false;
    }

    @Override
    public Adherent getAdherentById(Integer id) {
        return adherentRepository.findById(id).get();
    }

    @Override
    public Page<Adherent> searchAdherents(String keyword, int page, int taille) {
        return adherentRepository.findByNameContains(keyword, PageRequest.of(page,taille));
    }

    @Override
    public Adherent getAdherentByName(String name) {
        return adherentRepository.findByName(name);
    }
}
