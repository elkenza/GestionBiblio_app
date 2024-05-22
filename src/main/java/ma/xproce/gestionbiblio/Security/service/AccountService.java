package ma.xproce.gestionbiblio.Security.service;

import ma.xproce.gestionbiblio.Security.entities.Role;
import ma.xproce.gestionbiblio.Security.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public User createUser(String username,String email, String password,String confirmPassword );
    public Role createRole(String role);
    public void addRoleToUser(String username,String role);
    public void removeRoleFromUser(String username,String role);
    public User loadUserByUsername(String username);

}
