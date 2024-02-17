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
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JComboBox<Integer> trainNumberComboBox;
    private JComboBox<String> classComboBox;

    public TrajetGUI() {
        setTitle("Trajet Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window on the screen


        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("From");
        tableModel.addColumn("To");
        tableModel.addColumn("Train Number");
        tableModel.addColumn("Class");
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
                addRow();
            }
        });

        JButton modifyButton = new JButton("Modify");
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyRow();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRow();
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

        // Load data into the UI
        loadTrajetData();
    }

    private void loadTrajetData() {
        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM train")) {

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("ID"));
                row.add(resultSet.getString("from_location"));
                row.add(resultSet.getString("to_location"));
                row.add(resultSet.getInt("num_train"));
                row.add(resultSet.getString("class_train"));
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

    private void addRow() {
        JFrame addFrame = new JFrame("Add Trajet");
        addFrame.setSize(500, 500);
        addFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField idField = new JTextField(10);
        fromComboBox = new JComboBox<>();
        toComboBox = new JComboBox<>();
        trainNumberComboBox = new JComboBox<>();
        classComboBox = new JComboBox<>();
        JTextField departureField = new JTextField(10);
        JTextField arrivalField = new JTextField(10);
        JTextField differenceField = new JTextField(10);
        JTextField priceField = new JTextField(10);

        populateComboBoxes();

        addFrame.add(new JLabel("ID:"), gbc);
        gbc.gridx++;
        addFrame.add(idField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        addFrame.add(new JLabel("From:"), gbc);
        gbc.gridx++;
        addFrame.add(fromComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        addFrame.add(new JLabel("To:"), gbc);
        gbc.gridx++;
        addFrame.add(toComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        addFrame.add(new JLabel("Train Number:"), gbc);
        gbc.gridx++;
        addFrame.add(trainNumberComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        addFrame.add(new JLabel("Class:"), gbc);
        gbc.gridx++;
        addFrame.add(classComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        addFrame.add(new JLabel("Departure Time:"), gbc);
        gbc.gridx++;
        addFrame.add(departureField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        addFrame.add(new JLabel("Arrival Time:"), gbc);
        gbc.gridx++;
        addFrame.add(arrivalField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        addFrame.add(new JLabel("Time Difference:"), gbc);
        gbc.gridx++;
        addFrame.add(differenceField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        addFrame.add(new JLabel("Price:"), gbc);
        gbc.gridx++;
        addFrame.add(priceField, gbc);

        JButton saveButton = new JButton("Save");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        addFrame.add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Object> newRow = new Vector<>();
                newRow.add(idField.getText());
                newRow.add(fromComboBox.getSelectedItem());
                newRow.add(toComboBox.getSelectedItem());
                newRow.add(trainNumberComboBox.getSelectedItem());
                newRow.add(classComboBox.getSelectedItem());
                newRow.add(departureField.getText());
                newRow.add(arrivalField.getText());
                newRow.add(differenceField.getText());
                newRow.add(priceField.getText());

                tableModel.addRow(newRow);

                try (Connection connection = DbConnector.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO trajet (ID, from_location, to_location, num_train, class_train, departure_time, arrival_time, time_difference, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

                    preparedStatement.setString(1, idField.getText());
                    preparedStatement.setInt(2, fromComboBox.getSelectedIndex() + 1);
                    preparedStatement.setInt(3, toComboBox.getSelectedIndex() + 1);
                    preparedStatement.setInt(4, (int) trainNumberComboBox.getSelectedItem());
                    preparedStatement.setString(5, classComboBox.getSelectedItem().toString());
                    preparedStatement.setString(6, departureField.getText());
                    preparedStatement.setString(7, arrivalField.getText());
                    preparedStatement.setString(8, differenceField.getText());
                    preparedStatement.setString(9, priceField.getText());

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Row added to database successfully.");
                    } else {
                        System.out.println("Failed to add row to database.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                addFrame.dispose();
            }
        });

        addFrame.setVisible(true);
    }

    private void modifyRow() {
        JFrame modifyFrame = new JFrame("Modify Trajet");
        modifyFrame.setSize(500, 500);
        modifyFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField idField = new JTextField(10);
        fromComboBox = new JComboBox<>();
        toComboBox = new JComboBox<>();
        trainNumberComboBox = new JComboBox<>();
        classComboBox = new JComboBox<>();
        JTextField departureField = new JTextField(10);
        JTextField arrivalField = new JTextField(10);
        JTextField differenceField = new JTextField(10);
        JTextField priceField = new JTextField(10);

        populateComboBoxes();

        modifyFrame.add(new JLabel("ID:"), gbc);
        gbc.gridx++;
        modifyFrame.add(idField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        modifyFrame.add(new JLabel("From:"), gbc);
        gbc.gridx++;
        modifyFrame.add(fromComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        modifyFrame.add(new JLabel("To:"), gbc);
        gbc.gridx++;
        modifyFrame.add(toComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        modifyFrame.add(new JLabel("Train Number:"), gbc);
        gbc.gridx++;
        modifyFrame.add(trainNumberComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        modifyFrame.add(new JLabel("Class:"), gbc);
        gbc.gridx++;
        modifyFrame.add(classComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        modifyFrame.add(new JLabel("Departure Time:"), gbc);
        gbc.gridx++;
        modifyFrame.add(departureField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        modifyFrame.add(new JLabel("Arrival Time:"), gbc);
        gbc.gridx++;
        modifyFrame.add(arrivalField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        modifyFrame.add(new JLabel("Time Difference:"), gbc);
        gbc.gridx++;
        modifyFrame.add(differenceField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        modifyFrame.add(new JLabel("Price:"), gbc);
        gbc.gridx++;
        modifyFrame.add(priceField, gbc);

        JButton modifyConfirmButton = new JButton("Modify");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        modifyFrame.add(modifyConfirmButton, gbc);

        modifyConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idToModify = idField.getText();
                if (!idToModify.isEmpty()) {
                    int rowIndex = -1;
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        if (tableModel.getValueAt(i, 0).toString().equals(idToModify)) {
                            rowIndex = i;
                            break;
                        }
                    }
                    if (rowIndex != -1) {
                        if (!fromComboBox.getSelectedItem().toString().isEmpty()) {
                            tableModel.setValueAt(fromComboBox.getSelectedItem(), rowIndex, 1);
                        }
                        if (!toComboBox.getSelectedItem().toString().isEmpty()) {
                            tableModel.setValueAt(toComboBox.getSelectedItem(), rowIndex, 2);
                        }
                        if (!trainNumberComboBox.getSelectedItem().toString().isEmpty()) {
                            tableModel.setValueAt(trainNumberComboBox.getSelectedItem(), rowIndex, 3);
                        }
                        if (!classComboBox.getSelectedItem().toString().isEmpty()) {
                            tableModel.setValueAt(classComboBox.getSelectedItem(), rowIndex, 4);
                        }
                        if (!departureField.getText().isEmpty()) {
                            tableModel.setValueAt(departureField.getText(), rowIndex, 5);
                        }
                        if (!arrivalField.getText().isEmpty()) {
                            tableModel.setValueAt(arrivalField.getText(), rowIndex, 6);
                        }
                        if (!differenceField.getText().isEmpty()) {
                            tableModel.setValueAt(differenceField.getText(), rowIndex, 7);
                        }
                        if (!priceField.getText().isEmpty()) {
                            tableModel.setValueAt(priceField.getText(), rowIndex, 8);
                        }

                        try (Connection connection = DbConnector.getConnection();
                             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE trajet SET from_location=?, to_location=?, num_train=?, class_train=?, departure_time=?, arrival_time=?, time_difference=?, price=? WHERE ID=?")) {
                            preparedStatement.setInt(9, Integer.parseInt(idToModify));
                            preparedStatement.setInt(1, fromComboBox.getSelectedIndex() + 1);
                            preparedStatement.setInt(2, toComboBox.getSelectedIndex() + 1);
                            preparedStatement.setInt(3, (int) trainNumberComboBox.getSelectedItem());
                            preparedStatement.setString(4, classComboBox.getSelectedItem().toString());
                            preparedStatement.setString(5, departureField.getText().isEmpty() ? tableModel.getValueAt(rowIndex, 5).toString() : departureField.getText());
                            preparedStatement.setString(6, arrivalField.getText().isEmpty() ? tableModel.getValueAt(rowIndex, 6).toString() : arrivalField.getText());
                            preparedStatement.setString(7, differenceField.getText().isEmpty() ? tableModel.getValueAt(rowIndex, 7).toString() : differenceField.getText());
                            preparedStatement.setString(8, priceField.getText().isEmpty() ? tableModel.getValueAt(rowIndex, 8).toString() : priceField.getText());

                            int rowsAffected = preparedStatement.executeUpdate();
                            if (rowsAffected > 0) {
                                System.out.println("Row modified in database successfully.");
                            } else {
                                System.out.println("No rows modified in database.");
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

                modifyFrame.dispose();
            }
        });

        modifyFrame.setVisible(true);
    }

    private void deleteRow() {
        JFrame deleteFrame = new JFrame("Delete Trajet");
        deleteFrame.setSize(300, 100);
        deleteFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField idToDeleteField = new JTextField(10);

        deleteFrame.add(new JLabel("ID to delete:"), gbc);
        gbc.gridx++;
        deleteFrame.add(idToDeleteField, gbc);

        JButton deleteConfirmButton = new JButton("Delete");
        gbc.gridx++;
        deleteFrame.add(deleteConfirmButton, gbc);

        deleteConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idToDelete = idToDeleteField.getText();
                if (!idToDelete.isEmpty()) {
                    int rowIndex = -1;
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        if (tableModel.getValueAt(i, 0).toString().equals(idToDelete)) {
                            rowIndex = i;
                            break;
                        }
                    }
                    if (rowIndex != -1) {
                        tableModel.removeRow(rowIndex);
                    }

                    try (Connection connection = DbConnector.getConnection();
                         PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM trajet WHERE ID = ?")) {
                        preparedStatement.setInt(1, Integer.parseInt(idToDelete));
                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Row deleted from database successfully.");
                        } else {
                            System.out.println("No rows deleted from database.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

                deleteFrame.dispose();
            }
        });

        deleteFrame.setVisible(true);
    }

    private void populateComboBoxes() {
        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT nom_gare FROM gare")) {

            while (resultSet.next()) {
                String gareName = resultSet.getString("nom_gare");
                fromComboBox.addItem(gareName);
                toComboBox.addItem(gareName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT num_train FROM train")) {

            while (resultSet.next()) {
                int trainNumber = resultSet.getInt("num_train");
                trainNumberComboBox.addItem(trainNumber);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DbConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT DISTINCT class_train FROM train")) {

            while (resultSet.next()) {
                String classTrain = resultSet.getString("class_train");
                classComboBox.addItem(classTrain);
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
    private static final String PASSWORD = "ONCF@2024@ONCF";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}
