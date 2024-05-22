package ma.xproce.gestionbiblio.dao.repositories;

import jakarta.transaction.Transactional;
import ma.xproce.gestionbiblio.dao.entities.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface AbonnementRepository extends JpaRepository<Abonnement,Integer> {
}
