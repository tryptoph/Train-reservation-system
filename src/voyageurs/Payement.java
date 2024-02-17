package voyageurs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe pour la fenêtre de paiement
class Payement extends JFrame {
    public Payement() {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(Color.WHITE);

        // Ajout de l'en-tête
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(239, 46, 65));
        JLabel headerLabel = new JLabel("Paiement");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        content.add(headerPanel, BorderLayout.NORTH);

        // Ajout des composants pour le paiement
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new GridBagLayout());
        paymentPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Nom du porteur:");
        JTextField nameField = new JTextField(20);
        paymentPanel.add(nameLabel, gbc);
        gbc.gridx++;
        paymentPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel cardNumberLabel = new JLabel("Numéro de carte:");
        JTextField cardNumberField = new JTextField(20);
        paymentPanel.add(cardNumberLabel, gbc);
        gbc.gridx++;
        paymentPanel.add(cardNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel verificationLabel = new JLabel("Numéro de vérification:");
        JTextField verificationField = new JTextField(20);
        paymentPanel.add(verificationLabel, gbc);
        gbc.gridx++;
        paymentPanel.add(verificationField, gbc);

        content.add(paymentPanel, BorderLayout.CENTER);

        // Ajout des boutons "Valider" et "Annuler"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton validateButton = new JButton("Valider");
        validateButton.setPreferredSize(new Dimension(120, 40));
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Créez et affichez la fenêtre TicketPrinter lorsque le paiement est validé
            	Reservations reservation = new Reservations();
                TicketPrinter ticketPrinter = new TicketPrinter(reservation);
                ticketPrinter.setVisible(true);
                dispose(); // Ferme la fenêtre de paiement
            }
        });
        buttonPanel.add(validateButton);

        JButton cancelButton = new JButton("Annuler");
        cancelButton.setPreferredSize(new Dimension(120, 40));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la fenêtre de paiement
            }
        });
        buttonPanel.add(cancelButton);

        content.add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Paiement");
        setContentPane(content);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
}
