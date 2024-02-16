package voyageurs;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class CardHoraires extends JPanel {
	private final String gareDepart;
    private final String gareArrivee;
    private final TrajetDAO trajetDAO;
    
    public CardHoraires(String gareDepart, String gareArrivee) {
    	this.gareDepart = gareDepart;
        this.gareArrivee = gareArrivee;
        this.trajetDAO = new TrajetDAO();
        setLayout(new BorderLayout());

        // Créez un panneau d'en-tête rouge
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());
        headerPanel.setBackground(new Color(239, 46, 65));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 100));

        // Ajoutez une étiquette au panneau d'en-tête
        JLabel headerLabel = new JLabel("ONCF Voyages");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);

        GridBagConstraints gbcTexte = new GridBagConstraints();
        gbcTexte.gridx = 0;
        gbcTexte.gridy = 0;
        gbcTexte.weightx = 1.0;
        gbcTexte.weighty = 1.0;
        gbcTexte.anchor = GridBagConstraints.NORTHWEST;
        gbcTexte.insets = new Insets(40, 50, 10, 10);
        headerPanel.add(headerLabel, gbcTexte);

        // Ajoutez le panneau d'en-tête à ResultatsTrajetsPanel
        add(headerPanel, BorderLayout.NORTH);

     // Convertissez les chaînes en objets Trajet (simulation)
        JPanel trajetsPanel = new JPanel();
        trajetsPanel.setLayout(new BoxLayout(trajetsPanel, BoxLayout.Y_AXIS));

        List<TrajetDAO.Trajet> trajets = trajetDAO.rechercherTrajets(gareDepart, gareArrivee);
        for (TrajetDAO.Trajet trajet : trajets) {
            // Utilisez TripCardPanel pour afficher les informations du trajet
            TrajetsHoraires tripCardPanel = new TrajetsHoraires(trajet);
            trajetsPanel.add(tripCardPanel);
            trajetsPanel.add(Box.createVerticalStrut(10)); // Ajoutez un espace entre les trajets
        }
        // Ajoutez le panneau des trajets à ResultatsTrajetsPanel avec défilement
        JScrollPane scrollPane = new JScrollPane(trajetsPanel);
        add(scrollPane, BorderLayout.CENTER);
    }


    public static void afficherResultats(String gareDepart, String gareArrivee) {
        SwingUtilities.invokeLater(() -> {
            // Créez une instance de ResultatsTrajetsPanel avec les résultats des trajets
        	CardHoraires resultatsPanel = new CardHoraires(gareDepart, gareArrivee);


            // Affichez le panneau des résultats des trajets dans une nouvelle fenêtre
            JFrame resultatsFrame = new JFrame("Résultats des Trajets");
            resultatsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            resultatsFrame.getContentPane().add(resultatsPanel);
            resultatsFrame.setSize(1200, 600);
            resultatsFrame.setLocationRelativeTo(null); // Centrez la fenêtre
            resultatsFrame.setVisible(true);
        });
    }

    // Classe interne pour représenter un trajet
    public static class Trajet {
        protected String gareDepart;
        protected LocalTime heureDepart;
        protected LocalTime heureArrivee;
        protected String gareArrivee;
        protected LocalTime duree;
        protected int prix;

        public Trajet(String gareDepart, LocalTime heureDepart, LocalTime heureArrivee, String gareArrivee,LocalTime duree, int prix) {
            this.gareDepart = gareDepart;
            this.heureDepart = heureDepart;
            this.heureArrivee = heureArrivee;
            this.gareArrivee = gareArrivee;
            this.duree=duree;
            this.prix = prix;
        }
        
        public String getHeureDepart() {
            return heureDepart.format(DateTimeFormatter.ofPattern("HH:mm"));
        }

        public String getHeureArrivee() {
            return heureArrivee.format(DateTimeFormatter.ofPattern("HH:mm"));
        }

        public String getDuree() {
            long minutes = heureDepart.until(heureArrivee, java.time.temporal.ChronoUnit.MINUTES);
            long heures = minutes / 60;
            minutes %= 60;
            return String.format("%02d:%02d", heures, minutes);
        }
    }

}


