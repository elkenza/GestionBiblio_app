package ma.xproce.gestionbiblio.service;

import ma.xproce.gestionbiblio.dao.entities.Abonnement;
import ma.xproce.gestionbiblio.dao.repositories.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AbonnementManagerService implements AbonnementManager{
    @Autowired
    AbonnementRepository abonnementRepository;
    @Override
    public Abonnement addAbonnement(Abonnement abonnement) {
        try {
            return  abonnementRepository.save(abonnement);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Abonnement updateAbonnement(Abonnement abonnement) {
        Abonnement abo= abonnementRepository.findById(abonnement.getIdAbon()).get();
        abo.setAdherent(abonnement.getAdherent());
        abo.setName(abonnement.getName());
        abo.setTele(abonnement.getTele());
        return abo;
    }

    @Override
    public boolean deleteAbonnement(Abonnement abonnement) {
        if(abonnementRepository.existsById(abonnement.getIdAbon())){
            abonnementRepository.delete(abonnement);
            return true;
        }else
          return false;
    }

    @Override
    public boolean deleteAbonnementById(Integer id) {
        if(abonnementRepository.existsById(id)){
            abonnementRepository.deleteById(id);
            return true;
        }else
         return false;
    }

    @Override
    public Abonnement getAbonnementById(Integer id) {
        return abonnementRepository.findById(id).get();
    }

    @Override
    public List<Abonnement> getAllAbonnement() {
        return abonnementRepository.findAll();
    }
}
