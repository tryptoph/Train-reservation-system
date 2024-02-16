// TrajetDAO.java
package voyageurs;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TrajetDAO {
    // URL de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/train";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "ONCF@2024@ONCF";

    // Requête SQL pour récupérer les trajets en fonction des gares de départ et d'arrivée
    private static final String REQUETE_RECHERCHE_TRAJETS = "SELECT * FROM trajet WHERE `From_location` = ? AND `To_location` = ?";

    public List<Trajet> rechercherTrajets(String gareDepart, String gareArrivee) {
        List<Trajet> listeTrajets = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement preparedStatement = connection.prepareStatement(REQUETE_RECHERCHE_TRAJETS)) {

            preparedStatement.setString(1, gareDepart);
            preparedStatement.setString(2, gareArrivee);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String gareDepartResult = resultSet.getString("From_location");
                    LocalTime heureDepartResult = resultSet.getTime("departure_time").toLocalTime();
                    LocalTime heureArriveeResult = resultSet.getTime("arrival_time").toLocalTime();
                    String gareArriveeResult = resultSet.getString("To_location");
                    LocalTime dureeResult = resultSet.getTime("time_difference").toLocalTime();
                    int prixResult = resultSet.getInt("Price");

                    Trajet trajet = new Trajet(gareDepartResult, heureDepartResult, heureArriveeResult, gareArriveeResult, dureeResult, prixResult);
                    listeTrajets.add(trajet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listeTrajets;
    }

    // Classe interne pour représenter un trajet
    public static class Trajet {
        protected String gareDepart;
        protected LocalTime heureDepart;
        protected LocalTime heureArrivee;
        protected String gareArrivee;
        protected LocalTime duree;
        protected int prix;

        public Trajet(String gareDepart, LocalTime heureDepart, LocalTime heureArrivee, String gareArrivee, LocalTime duree, int prix) {
            this.gareDepart = gareDepart;
            this.heureDepart = heureDepart;
            this.heureArrivee = heureArrivee;
            this.gareArrivee = gareArrivee;
            this.duree=duree;
            this.prix = prix;
        }

        // Getters et autres méthodes selon les besoins
    }
}
