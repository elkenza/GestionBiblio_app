package ma.xproce.gestionbiblio.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity

                .authorizeHttpRequests(
                        authCustomizer -> authCustomizer
                                .requestMatchers("/ajouterAuteur","/ajouterLivrePourAuteur","/editAuteur","deleteAuteur","/deleteAdherent","/editAdherent","editBook","deleteBook","/ajouterBook","/ajouterLivrePourAuteur","modifierAuteur","editAdherent","edit","ajout","/editAdherent","/listEmprunts","deleteEmprunt","/listeAdherents").hasRole("ADMIN")
                                .requestMatchers("addIndividu","editIndividu","/listeAuteurs","/editAdherent","/","addEmprunt","/profile").hasAnyRole("ADMIN","USER")
                                .requestMatchers("/login","/webjars/**","test","/register","submit_register").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/indexpage", true)

                        )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )


                .exceptionHandling(e->e.accessDeniedPage("/accessDenied"))
                .build();

    }
  //  @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(bCryptPasswordEncoder().encode("123")).roles("ADMIN").build(),
                User.withUsername("user").password(bCryptPasswordEncoder().encode("1234")).roles("user").build()

        );
    }
}
