package ma.xproce.gestionbiblio.Security.repositories;


import jakarta.transaction.Transactional;
import ma.xproce.gestionbiblio.Security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
public interface UserRepository extends JpaRepository<User,String> {
    public User findByUserName(String username);
}
