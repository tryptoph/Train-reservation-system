package voyageurs;

import java.util.Date;
import java.util.List;

public class Reservations {
	private String gareDepart;
    private String gareArrivee;
    protected String dateDepart;
    private String dateRetour; 
    private int nombreAdultes;
    private int nombreEnfants;
    private String confortSelectionne;
	private List<String> listeTrajets;
    
    public Reservations() {
        // Initialisation des champs si nécessaire
    }
    public Reservations(String gareDepart, String gareArrivee, String dateDepart, String dateRetour, int nombreAdultes, int nombreEnfants, String confortSelectionne) {
        this.gareDepart = gareDepart;
        this.gareArrivee = gareArrivee;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.nombreAdultes = nombreAdultes;
        this.nombreEnfants = nombreEnfants;
        this.confortSelectionne = confortSelectionne;
    }

    // Méthode pour définir la gare de départ
    public void setGareDepart(String gareDepart) {
        this.gareDepart = gareDepart;
    }
    public void setGareArrivee(String gareArrivee) {
        this.gareArrivee = gareArrivee;
    }

    public String getDateDepart() {
		return dateDepart;
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
    	// Appel à TrajetDAO pour obtenir les résultats des trajets
        TrajetDAO trajetDAO = new TrajetDAO();
        List<TrajetDAO.Trajet> listeTrajets = trajetDAO.rechercherTrajets(gareDepart, gareArrivee);

        // Affichez les résultats dans la console à titre de vérification
        for (TrajetDAO.Trajet trajet : listeTrajets) {
            System.out.println(trajet);
        }
        
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

