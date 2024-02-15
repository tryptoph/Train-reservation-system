package voyageur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicketPrinter extends JFrame {
    public TicketPrinter() {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(Color.WHITE);

        // Ajout de l'en-tête
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(239, 46, 65));
        JLabel headerLabel = new JLabel("Impression du Ticket");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        content.add(headerPanel, BorderLayout.NORTH);

        // Ajout d'un texte d'information
        JTextArea infoText = new JTextArea();
        infoText.setText("Veuillez insérer le papier dans l'imprimante et cliquer sur le bouton 'Imprimer'.");
        infoText.setEditable(false);
        infoText.setOpaque(false);
        infoText.setFont(new Font("Arial", Font.PLAIN, 16));
        infoText.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        content.add(infoText, BorderLayout.CENTER);

        // Ajout du bouton "Imprimer"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton printButton = new JButton("Imprimer");
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ajouter ici le code pour imprimer le ticket
                JOptionPane.showMessageDialog(null, "Le ticket a été imprimé avec succès !");
            }
        });
        buttonPanel.add(printButton);
        content.add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Impression du Ticket");
        setContentPane(content);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicketPrinter().setVisible(true);
            }
        });
    }
}
