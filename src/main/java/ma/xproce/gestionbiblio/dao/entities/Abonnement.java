package ma.xproce.gestionbiblio.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdAbon;
    private String Name;
    private Integer Tele;
    @ManyToOne
    private Adherent adherent;
}
