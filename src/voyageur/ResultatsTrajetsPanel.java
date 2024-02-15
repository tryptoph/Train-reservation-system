package voyageur;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ResultatsTrajetsPanel extends JPanel {
    public ResultatsTrajetsPanel(List<String> listeTrajets) {
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
        List<Trajet> trajets = convertirChainesEnTrajets(listeTrajets);

     // Créez un panneau pour afficher les trajets
        JPanel trajetsPanel = new JPanel();
        trajetsPanel.setLayout(new BoxLayout(trajetsPanel, BoxLayout.Y_AXIS));

        // Ajoutez chaque trajet à la liste
        for (ResultatsTrajetsPanel.Trajet trajet : trajets) {
            // Utilisez TripCardPanel pour afficher les informations du trajet
            TripCardPanel tripCardPanel = new TripCardPanel(trajet);
            trajetsPanel.add(tripCardPanel);
            trajetsPanel.add(Box.createVerticalStrut(10)); // Ajoutez un espace entre les trajets
        }

        // Ajoutez le panneau des trajets à ResultatsTrajetsPanel avec défilement
        JScrollPane scrollPane = new JScrollPane(trajetsPanel);
        add(scrollPane, BorderLayout.CENTER);
        }

    public static void afficherResultats(List<String> listeTrajets) {
        SwingUtilities.invokeLater(() -> {
            // Créez une instance de ResultatsTrajetsPanel avec les résultats des trajets
            ResultatsTrajetsPanel resultatsPanel = new ResultatsTrajetsPanel(listeTrajets);

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
        protected int prix;

        public Trajet(String gareDepart, LocalTime heureDepart, LocalTime heureArrivee, String gareArrivee, int prix) {
            this.gareDepart = gareDepart;
            this.heureDepart = heureDepart;
            this.heureArrivee = heureArrivee;
            this.gareArrivee = gareArrivee;
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

    
    private List<Trajet> convertirChainesEnTrajets(List<String> listeTrajets) {
        // Simulation de la conversion des chaînes en objets Trajet
        // Vous devrez adapter cela en fonction de votre structure de données réelle
        return Arrays.asList(
                new Trajet("Casa Voyageurs", LocalTime.parse("08:00"), LocalTime.parse("10:30"), "Rabat Agdal", 85),
                new Trajet("Casa Voyageurs", LocalTime.parse("12:30"), LocalTime.parse("15:45"), "Rabat Agdal", 90),
                new Trajet("Casa Voyageurs", LocalTime.parse("18:15"), LocalTime.parse("20:45"), "Rabat Agdal", 100)
        );
    }
}


