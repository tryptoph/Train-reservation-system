package admin;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class TrajetGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public TrajetGUI() {
        setTitle("Trajet Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("From");
        tableModel.addColumn("To");
        tableModel.addColumn("Departure Time");
        tableModel.addColumn("Arrival Time");
        tableModel.addColumn("Time Difference");
        tableModel.addColumn("Price");

        table = new JTable(tableModel);
        table.setBackground(new Color(255, 165, 0)); // Set the background color of the table to orange
        table.getTableHeader().setBackground(new Color(239, 46, 65)); // Set the background color of the table header
        JScrollPane scrollPane = new JScrollPane(table);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement add functionality
            }
        });

        JButton modifyButton = new JButton("Modify");
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement modify functionality
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement delete functionality
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Set the background color of the content pane
        getContentPane().setBackground(new Color(255, 165, 0)); // Orange

        loadTrajetData();
    }

    private void loadTrajetData() {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM trajet");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("ID"));
                row.add(resultSet.getString("from_location"));
                row.add(resultSet.getString("to_location"));
                row.add(resultSet.getTime("departure_time"));
                row.add(resultSet.getTime("arrival_time"));
                row.add(resultSet.getTime("time_difference"));
                row.add(resultSet.getDouble("price"));
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TrajetGUI().setVisible(true);
            }
        });
    }
}

class DbConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/train";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}
