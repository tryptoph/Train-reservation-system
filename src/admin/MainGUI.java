package admin ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame {
    private JButton beneficiaryButton;
    private JButton discountCardButton;
    private JButton dashboardButton;
    private JButton trajetButton;

    public MainGUI() {
        setTitle("ONCF Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(239, 46, 65)); // Red-like coloration

        // Add ONCF logo text
        JLabel logoLabel = new JLabel("ONCF", SwingConstants.CENTER);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 30));
        logoLabel.setForeground(Color.WHITE);
        mainPanel.add(logoLabel);

        // Add buttons
        trajetButton = createButton("Trajet", "🚆");
        beneficiaryButton = createButton("Beneficiary", "👤");
        discountCardButton = createButton("Discount Card", "💳");
        dashboardButton = createButton("Dashboard", "📊");

        // Add action listeners to buttons
        trajetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTrajetGUI();
            }
        });

        beneficiaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBeneficiary();
            }
        });

        discountCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDiscountCardGUI();
            }
        });

        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDashboard();
            }
        });

        // Add buttons to the main panel
        mainPanel.add(trajetButton);
        mainPanel.add(beneficiaryButton);
        mainPanel.add(discountCardButton);
        mainPanel.add(dashboardButton);

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Set the background color of the content pane
        getContentPane().setBackground(new Color(239, 46, 65)); // Red-like coloration
    }

    // Method to create buttons with text and symbol
    private JButton createButton(String text, String symbol) {
        JButton button = new JButton("<html><center>" + symbol + "<br>" + text + "</center></html>");
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(46, 49, 146)); // Dark blue background
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding
        return button;
    }

    // Methods to open different GUI windows
    private void openBeneficiary() {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setVisible(true);
        beneficiary.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Change to DISPOSE_ON_CLOSE
    }

    private void openDiscountCardGUI() {
        DiscountCardGUI discountCardGUI = new DiscountCardGUI();
        discountCardGUI.setVisible(true);
        discountCardGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Change to DISPOSE_ON_CLOSE
    }

    private void openDashboard() {
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
        dashboard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Change to DISPOSE_ON_CLOSE
    }

    private void openTrajetGUI() {
        TrajetGUI trajetGUI = new TrajetGUI();
        trajetGUI.setVisible(true);
        trajetGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Change to DISPOSE_ON_CLOSE
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainGUI mainGUI = new MainGUI();
                mainGUI.setVisible(true);
            }
        });
    }
}
