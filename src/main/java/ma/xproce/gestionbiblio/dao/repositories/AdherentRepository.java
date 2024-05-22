package ma.xproce.gestionbiblio.dao.repositories;


import jakarta.transaction.Transactional;

import ma.xproce.gestionbiblio.dao.entities.Adherent;
import ma.xproce.gestionbiblio.dao.entities.Auteur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface AdherentRepository extends JpaRepository<Adherent,Integer> {
    Page<Adherent> findByNameContains(String keyword, Pageable pageable);
    Adherent findByName(String name);
}
