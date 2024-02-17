package admin;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class DiscountCardGUI extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JTable discountCardTable;
    private DefaultTableModel discountCardTableModel;

    public DiscountCardGUI() {
        setTitle("Discount Card Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window on the screen

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Create panel for discount card
        JPanel discountCardPanel = createDiscountCardPanel();
        mainPanel.add(discountCardPanel, "DiscountCard");

        // Display discount card panel by default
        cardLayout.show(mainPanel, "DiscountCard");

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createDiscountCardPanel() {
        JPanel discountCardPanel = new JPanel(new BorderLayout());

        // Create table for displaying discount cards
        discountCardTableModel = new DefaultTableModel();
        discountCardTableModel.addColumn("ID");
        discountCardTableModel.addColumn("Card Type");
        discountCardTableModel.addColumn("Validity");
        discountCardTableModel.addColumn("Reduction Percentage");
        discountCardTable = new JTable(discountCardTableModel);
        JScrollPane discountCardScrollPane = new JScrollPane(discountCardTable);
        discountCardPanel.add(discountCardScrollPane, BorderLayout.CENTER);

        // Buttons for discount card operations
        JPanel discountCardButtonPanel = new JPanel(new FlowLayout());
        JButton addDiscountCardButton = new JButton("Add");
        JButton modifyDiscountCardButton = new JButton("Modify");
        JButton deleteDiscountCardButton = new JButton("Delete");
        JButton exitButton = new JButton("Exit");

        addDiscountCardButton.addActionListener(e -> addDiscountCard());
        modifyDiscountCardButton.addActionListener(e -> modifyDiscountCard());
        deleteDiscountCardButton.addActionListener(e -> deleteDiscountCard());
        exitButton.addActionListener(e -> System.exit(0));

        discountCardButtonPanel.add(addDiscountCardButton);
        discountCardButtonPanel.add(modifyDiscountCardButton);
        discountCardButtonPanel.add(deleteDiscountCardButton);
        discountCardButtonPanel.add(exitButton);

        discountCardPanel.add(discountCardButtonPanel, BorderLayout.SOUTH);

        // Load discount card data initially
        loadDiscountCardData();

        return discountCardPanel;
    }

    private void loadDiscountCardData() {
        // Clear existing data
        discountCardTableModel.setRowCount(0);

        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM discount_card")) {

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("id"));
                row.add(resultSet.getString("card_type"));
                row.add(resultSet.getDate("validity"));
                row.add(resultSet.getInt("reduction_percentage"));
                discountCardTableModel.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addDiscountCard() {
        JTextField idField = new JTextField();
        JTextField cardTypeField = new JTextField();
        JTextField validityField = new JTextField();
        JTextField reductionPercentageField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Card Type:"));
        panel.add(cardTypeField);
        panel.add(new JLabel("Validity (YYYY-MM-DD):"));
        panel.add(validityField);
        panel.add(new JLabel("Reduction Percentage:"));
        panel.add(reductionPercentageField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Discount Card",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String cardType = cardTypeField.getText();
            String validity = validityField.getText();
            int reductionPercentage = Integer.parseInt(reductionPercentageField.getText());

            try (Connection connection = DbConnector.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO discount_card (id, card_type, validity, reduction_percentage) VALUES (?, ?, ?, ?)")) {
                statement.setInt(1, id);
                statement.setString(2, cardType);
                statement.setString(3, validity);
                statement.setInt(4, reductionPercentage);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Discount Card added successfully!");
                    loadDiscountCardData();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add discount card!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void modifyDiscountCard() {
        JTextField idField = new JTextField();
        JTextField cardTypeField = new JTextField();
        JTextField validityField = new JTextField();
        JTextField reductionPercentageField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Card Type:"));
        panel.add(cardTypeField);
        panel.add(new JLabel("Validity (YYYY-MM-DD):"));
        panel.add(validityField);
        panel.add(new JLabel("Reduction Percentage:"));
        panel.add(reductionPercentageField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Modify Discount Card",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String cardType = cardTypeField.getText();
            String validity = validityField.getText();
            int reductionPercentage = Integer.parseInt(reductionPercentageField.getText());

            try (Connection connection = DbConnector.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "UPDATE discount_card SET card_type = ?, validity = ?, reduction_percentage = ? WHERE id = ?")) {
                statement.setString(1, cardType);
                statement.setString(2, validity);
                statement.setInt(3, reductionPercentage);
                statement.setInt(4, id);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Discount Card modified successfully!");
                    loadDiscountCardData();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to modify discount card!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteDiscountCard() {
        String idString = JOptionPane.showInputDialog("Enter ID of the Discount Card to delete:");
        if (idString != null && !idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete Discount Card with ID " + id + "?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                try (Connection connection = DbConnector.getConnection();
                     PreparedStatement statement = connection.prepareStatement(
                             "DELETE FROM discount_card WHERE id = ?")) {
                    statement.setInt(1, id);

                    int rowsDeleted = statement.executeUpdate();
                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Discount Card deleted successfully!");
                        loadDiscountCardData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete discount card!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DiscountCardGUI::new);
    }
}
