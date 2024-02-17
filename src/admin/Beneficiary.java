package admin;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class Beneficiary extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JTable beneficiaryTable;
    private DefaultTableModel beneficiaryTableModel;
    private JComboBox<String> cardTypeComboBox;

    public Beneficiary() {
        setTitle("Beneficiary Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window on the screen

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Create panel for beneficiary
        JPanel beneficiaryPanel = createBeneficiaryPanel();
        mainPanel.add(beneficiaryPanel, "Beneficiary");

        // Display beneficiary panel by default
        cardLayout.show(mainPanel, "Beneficiary");

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createBeneficiaryPanel() {
        JPanel beneficiaryPanel = new JPanel(new BorderLayout());

        // Create table for displaying beneficiaries
        beneficiaryTableModel = new DefaultTableModel();
        beneficiaryTableModel.addColumn("User ID");
        beneficiaryTableModel.addColumn("Username");
        beneficiaryTableModel.addColumn("Email");
        beneficiaryTableModel.addColumn("Phone");
        beneficiaryTableModel.addColumn("Card Type");
        beneficiaryTable = new JTable(beneficiaryTableModel);
        JScrollPane beneficiaryScrollPane = new JScrollPane(beneficiaryTable);
        beneficiaryPanel.add(beneficiaryScrollPane, BorderLayout.CENTER);

        // Buttons for beneficiary operations
        JPanel beneficiaryButtonPanel = new JPanel(new FlowLayout());
        JButton addBeneficiaryButton = new JButton("Add");
        JButton modifyBeneficiaryButton = new JButton("Modify");
        JButton deleteBeneficiaryButton = new JButton("Delete");
        JButton exitButton = new JButton("Exit");

        addBeneficiaryButton.addActionListener(e -> addBeneficiary());
        modifyBeneficiaryButton.addActionListener(e -> modifyBeneficiary());
        deleteBeneficiaryButton.addActionListener(e -> deleteBeneficiary());
        exitButton.addActionListener(e -> dispose());

        beneficiaryButtonPanel.add(addBeneficiaryButton);
        beneficiaryButtonPanel.add(modifyBeneficiaryButton);
        beneficiaryButtonPanel.add(deleteBeneficiaryButton);
        beneficiaryButtonPanel.add(exitButton);

        beneficiaryPanel.add(beneficiaryButtonPanel, BorderLayout.SOUTH);

        // Load beneficiary data initially
        loadBeneficiaryData();

        return beneficiaryPanel;
    }

    private void loadBeneficiaryData() {
        // Clear existing data
        beneficiaryTableModel.setRowCount(0);

        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT b.user_id, b.username, b.email, b.phone, d.card_type FROM beneficiaire b JOIN discount_card d ON b.discount_card_id = d.id")) {

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("user_id"));
                row.add(resultSet.getString("username"));
                row.add(resultSet.getString("email"));
                row.add(resultSet.getString("phone"));
                row.add(resultSet.getString("card_type"));
                beneficiaryTableModel.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addBeneficiary() {
        JTextField idField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();

        // Create a combo box for card types
        cardTypeComboBox = new JComboBox<>();
        loadCardTypes();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("User ID:"));
        panel.add(idField);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Card Type:"));
        panel.add(cardTypeComboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Beneficiary",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String username = usernameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String selectedCardType = cardTypeComboBox.getSelectedItem().toString();

            try (Connection connection = DbConnector.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO beneficiaire (user_id, username, email, phone, discount_card_id) VALUES (?, ?, ?, ?, (SELECT id FROM discount_card WHERE card_type = ?))")) {
                statement.setInt(1, id);
                statement.setString(2, username);
                statement.setString(3, email);
                statement.setString(4, phone);
                statement.setString(5, selectedCardType);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Beneficiary added successfully!");
                    loadBeneficiaryData();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add beneficiary!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void modifyBeneficiary() {
        JTextField idField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();

        // Create a combo box for card types
        cardTypeComboBox = new JComboBox<>();
        loadCardTypes();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("User ID:"));
        panel.add(idField);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Card Type:"));
        panel.add(cardTypeComboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Modify Beneficiary",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String username = usernameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String selectedCardType = cardTypeComboBox.getSelectedItem().toString();

            try (Connection connection = DbConnector.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "UPDATE beneficiaire SET username = ?, email = ?, phone = ?, discount_card_id = (SELECT id FROM discount_card WHERE card_type = ?) WHERE user_id = ?")) {
                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, phone);
                statement.setString(4, selectedCardType);
                statement.setInt(5, id);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Beneficiary modified successfully!");
                    loadBeneficiaryData();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to modify beneficiary!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteBeneficiary() {
        String idString = JOptionPane.showInputDialog("Enter User ID of the Beneficiary to delete:");
        if (idString != null && !idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete Beneficiary with User ID " + id + "?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                try (Connection connection = DbConnector.getConnection();
                     PreparedStatement statement = connection.prepareStatement(
                             "DELETE FROM beneficiaire WHERE user_id = ?")) {
                    statement.setInt(1, id);

                    int rowsDeleted = statement.executeUpdate();
                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Beneficiary deleted successfully!");
                        loadBeneficiaryData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete beneficiary!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadCardTypes() {
        cardTypeComboBox.removeAllItems();
        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT card_type FROM discount_card")) {
            while (resultSet.next()) {
                cardTypeComboBox.addItem(resultSet.getString("card_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Beneficiary::new);
    }
}
