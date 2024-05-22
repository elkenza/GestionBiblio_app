package ma.xproce.gestionbiblio.dao.repositories;
import jakarta.transaction.Transactional;

import ma.xproce.gestionbiblio.dao.entities.Emprunt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface EmpruntRepository extends JpaRepository<Emprunt,Integer> {
    Page<Emprunt> findByIdEmpruntContains(Integer keyword, Pageable pageable);
}
