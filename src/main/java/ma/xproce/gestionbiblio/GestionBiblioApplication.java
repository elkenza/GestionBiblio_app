package ma.xproce.gestionbiblio;

import ma.xproce.gestionbiblio.Security.service.AccountService;
import ma.xproce.gestionbiblio.dao.entities.*;
import ma.xproce.gestionbiblio.dao.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@SpringBootApplication
public class GestionBiblioApplication {


    public static void main(String[] args) {
        SpringApplication.run(GestionBiblioApplication.class, args);
    }
    @Bean
    public CommandLineRunner start() {
        return args -> {

/*
        accountService.createUser("admin","admin@gmail.com","123","123");

        accountService.createUser("user","user@gmail.com","1234","1234");
        accountService.createRole("ADMIN");

        accountService.createRole("USER");
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("user","USER");*/
            // accountService.addRoleToUser("admin","USER");
            // accountService.removeRoleFromUser("admin","USER");
            //  System.out.println("heeeereeee");


        };
    }

}
