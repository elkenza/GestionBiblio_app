package ma.xproce.gestionbiblio.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import lombok.ToString;

import java.io.File;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBook;
    @NotEmpty
    @Pattern(regexp = "[A-Za-z0-9àé' ]*")
    private String title;
    private Integer nbPages;
    private String image;
    @ManyToMany
    @ToString.Exclude
    private Collection<Auteur> auteurs;
    @ManyToMany
    private Collection<Adherent> adherents;
    @OneToMany
    private Collection<Emprunt> emprunts;

}
