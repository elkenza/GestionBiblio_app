package ma.xproce.gestionbiblio.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAd;
    private String name;
    private  String tele;
    @ManyToMany
    private Collection<Book> livres;
    @OneToMany
    private Collection<Emprunt> emprunts;
    @OneToMany
    private Collection<Abonnement> abonnements;
}
