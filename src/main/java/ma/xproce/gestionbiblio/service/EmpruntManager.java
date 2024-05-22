package ma.xproce.gestionbiblio.service;

import ma.xproce.gestionbiblio.dao.entities.Book;
import ma.xproce.gestionbiblio.dao.entities.Emprunt;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmpruntManager {
    public Emprunt addEmprunt(Emprunt emprunt);
    public Emprunt updateEmprunt(Emprunt emprunt);
    public boolean deleteEmprunt(Emprunt emprunt);
    public boolean deleteEmpruntById(Integer id);
    public Emprunt getEmpruntById(Integer id);
    public List<Emprunt> getAllEmprunts();
    public Page<Emprunt> searchBook(Integer keyword, int page, int taille);
}
