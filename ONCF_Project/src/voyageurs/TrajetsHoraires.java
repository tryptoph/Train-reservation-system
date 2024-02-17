package voyageurs;

import javax.swing.*;

import voyageurs.CardHoraires.Trajet;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;

public class TrajetsHoraires extends JPanel {
    public TrajetsHoraires(TrajetDAO.Trajet trajet) {
        setLayout(new BorderLayout());
        // Top panel with departure imminent tag
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel Tag = new JLabel("ALLER");
        Tag.setFont(new Font("Arial", Font.BOLD, 14));
        Tag.setForeground(Color.WHITE);
        Tag.setBackground(new Color(239, 46, 65));
        Tag.setOpaque(true);
        topPanel.add(Tag);

        add(topPanel, BorderLayout.NORTH);

     // Center panel with trip information
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Utiliser BoxLayout en mode vertical

        // Departure info
        JPanel departurePanel = createInfoPanel("Départ", trajet.heureDepart, trajet.gareDepart);
        centerPanel.add(departurePanel, BorderLayout.EAST);
        
        JPanel arrivalPanel = createInfoPanel("Arrivée", trajet.heureArrivee, trajet.gareArrivee);
        centerPanel.add(arrivalPanel, BorderLayout.WEST);


     // Duration and route info
        JPanel durationRoutePanel = new JPanel();
        durationRoutePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel durationLabel = new JLabel("Durée:" + trajet.duree + " (direct)");

        durationRoutePanel.add(durationLabel);
        centerPanel.add(durationRoutePanel);
        
        
        add(centerPanel, BorderLayout.CENTER);
        // Divider
        add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
    }

  
    private JPanel createInfoPanel(String label, LocalTime heureDepart, String gareDepart) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel labelLabel = new JLabel(label + ": ");
        JLabel timeLabel = new JLabel(heureDepart.toString() + " ");
        JLabel stationLabel = new JLabel(gareDepart);

        infoPanel.add(labelLabel);
        infoPanel.add(timeLabel);
        infoPanel.add(stationLabel);

        return infoPanel;
    }
    
    

}

