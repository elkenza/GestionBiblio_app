package ma.xproce.gestionbiblio.dao.repositories;

import jakarta.transaction.Transactional;

import ma.xproce.gestionbiblio.dao.entities.Auteur;
import ma.xproce.gestionbiblio.dao.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface AuteurRepository extends JpaRepository<Auteur,Integer> {
    Page<Auteur> findByNameContains(String keyword, Pageable pageable);
    Auteur findAuteurByNameContains(String name);
}
