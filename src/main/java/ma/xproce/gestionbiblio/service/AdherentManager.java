package ma.xproce.gestionbiblio.service;

import ma.xproce.gestionbiblio.dao.entities.Adherent;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdherentManager {
    public Adherent addAdherent(Adherent adherent);
    public Adherent updateAdherent(Adherent adherent);
    public boolean deleteAdherent(Adherent adherent);
    public boolean deleteAdherentById(Integer id);
    public Adherent getAdherentById(Integer id);
    public Page<Adherent> searchAdherents(String keyword, int page, int taille);
    public Adherent getAdherentByName(String name);
}
