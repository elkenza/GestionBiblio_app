package ma.xproce.gestionbiblio.Security.repositories;


import ma.xproce.gestionbiblio.Security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    public Role findByRole(String newRole);
}
