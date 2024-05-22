package ma.xproce.gestionbiblio.Security.service;

import jakarta.transaction.Transactional;
import ma.xproce.gestionbiblio.Security.entities.Role;
import ma.xproce.gestionbiblio.Security.entities.User;
import ma.xproce.gestionbiblio.Security.repositories.RoleRepository;
import ma.xproce.gestionbiblio.Security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@Transactional
public class AccountManager implements AccountService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public User createUser(String username, String email, String password, String confirmPassword) {
      /* User user=new User();
       user.setUserId(UUID.randomUUID().toString());
       user.setUserName(username);
       user.setPassword(bCryptPasswordEncoder.encode(password));
       user.setEmail(email);*/
        User user1=userRepository.findByUserName(username);
        if(user1 != null) throw new RuntimeException("User already exits");
        if (!password.equals(confirmPassword)) throw new RuntimeException("Passwords do not match");
        user1= User
                .builder()
                .userName(username)
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .userId(UUID.randomUUID().toString())
                .build();
       return userRepository.save(user1);
    }

    @Override
    public Role createRole(String role) {
        Role role1=roleRepository.findByRole(role);
        if(role1 != null) throw new RuntimeException("role already exists");
        role1= Role.builder()
                .role(role)
                .build();
        return roleRepository.save(role1);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        User user =userRepository.findByUserName(username);
        Role role1=roleRepository.findByRole(role);

        if (user.getRoleList() == null) {
            user.setRoleList(new ArrayList<>());
        }

        user.getRoleList().add(role1);
        userRepository.save(user);

    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        User user =userRepository.findByUserName(username);
        Role role1=roleRepository.findByRole(role);

        user.getRoleList().remove(role1);

    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUserName(username);

    }
}
