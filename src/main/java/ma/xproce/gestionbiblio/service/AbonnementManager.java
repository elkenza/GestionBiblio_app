package ma.xproce.gestionbiblio.service;

import ma.xproce.gestionbiblio.dao.entities.Abonnement;

import java.util.List;

public interface AbonnementManager {
    public Abonnement addAbonnement(Abonnement abonnement);
    public Abonnement updateAbonnement(Abonnement abonnement);
    public boolean deleteAbonnement(Abonnement abonnement);
    public boolean deleteAbonnementById(Integer id);
    public Abonnement getAbonnementById(Integer id);
    public List<Abonnement> getAllAbonnement();

}
