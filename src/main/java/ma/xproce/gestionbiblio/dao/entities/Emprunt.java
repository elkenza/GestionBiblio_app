package ma.xproce.gestionbiblio.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmprunt;
    private LocalDate DateEmprunt;
    private LocalDate DateRetour;
    @ManyToOne
    private  Book book;
    @ManyToOne
    private Adherent adherent;
}
