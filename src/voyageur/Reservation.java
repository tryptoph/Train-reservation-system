package voyageur;

import java.util.Date;
import java.util.List;

public class Reservation {
	private String gareDepart;
    private String gareArrivee;
    private String dateDepart;
    private String dateRetour; 
    private int nombreAdultes;
    private int nombreEnfants;
    private String confortSelectionne;
	private List<String> listeTrajets;
    
    public Reservation() {
        // Initialisation des champs si nécessaire
    }

    // Méthode pour définir la gare de départ
    public void setGareDepart(String gareDepart) {
        this.gareDepart = gareDepart;
    }
    public void setGareArrivee(String gareArrivee) {
        this.gareArrivee = gareArrivee;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }
    
    public void setQuantiteAdultes(int nombreAdultes) {
        this.nombreAdultes = nombreAdultes;
    }

    public void setQuantiteEnfants(int nombreEnfants) {
        this.nombreEnfants = nombreEnfants;
    }
    
    public String getConfortSelectionne() {
		return confortSelectionne;
	}

	public void setConfortSelectionne(String confortSelectionne) {
        this.confortSelectionne = confortSelectionne;
    }
 
	public List<String> rechercherTrajets() {
	    // Logique de recherche des trajets ici
	    // Stockez les résultats dans une liste (par exemple, listeTrajets)
	    return listeTrajets;
	}
    // Méthode principale pour réservation
    public void reserverBillet() {
        // Ajoutez ici la logique spécifique pour la réservation
        // Utilisez les champs gareDepart, gareArrivee, dateDepart, dateRetour, etc.
        // Exemple :
        System.out.println("Réservation effectuée : ");
        System.out.println(" " + "ALLER LE:  " + dateDepart + "  De  " + gareDepart );
        System.out.println(" " + "ARRIVEE à:  "  + gareArrivee );
        if (dateRetour != null) {
        	System.out.println(" " + "Date De Retour:  " + dateRetour);
        }
        System.out.println(" " + "Nombre d'adultes : " + nombreAdultes);
        if (nombreEnfants != 0) {
        	System.out.println(" " + "Nombre d'enfants : " + nombreEnfants);
        }
        
        System.out.println(" " + "Confort sélectionné : " + confortSelectionne);
    }

}

