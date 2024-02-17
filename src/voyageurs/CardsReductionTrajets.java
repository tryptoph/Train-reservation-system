package voyageurs;

import javax.swing.*;
import java.awt.*;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class CardsReductionTrajets extends JPanel {
	private final Reservations reservations;
    private final TicketPrinter ticketPrinter;
    public CardsReductionTrajets(ReductionDAO.Trajet trajet, Reservations reservations, TicketPrinter ticketPrinter) {
    	this.reservations = reservations;
        this.ticketPrinter = ticketPrinter;
        
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
        
        // Price info
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        double a = trajet.pourcentageReduction / 100 ;
        double b = trajet.prix * a ;
        
        JLabel price = new JLabel("A partir de: "+ trajet.prix + " DH");
        pricePanel.add(price);
        centerPanel.add(pricePanel);

        // Reservation button
        JPanel reservationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton reservationButton = new JButton("Réserver");
        reservationPanel.add(reservationButton);
        reservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Créer et afficher la fenêtre de paiement lorsque le bouton "Réserver" est cliqué
                Payement paymentWindow = new Payement();
                paymentWindow.setVisible(true);

                // Passer les informations à la fenêtre TicketPrinter
                ticketPrinter.displayReservationDetails1(reservations,trajet);
            }
        });
        centerPanel.add(reservationPanel);

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

