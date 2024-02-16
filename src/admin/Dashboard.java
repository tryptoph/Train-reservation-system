package admin;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jfree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.Desktop;
import java.io.File;

public class Dashboard extends JFrame {
    private static class DbConnector {
        private static final String JDBC_URL = "jdbc:mysql://localhost:3306/train";
        private static final String USER = "root";
        private static final String PASSWORD = " ";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        }
    }

    private JTabbedPane tabbedPane;

    public Dashboard() {
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Création du menu "Data" dans la barre latérale à gauche
        JPanel dataMenuPanel = new JPanel();
        dataMenuPanel.setLayout(new BoxLayout(dataMenuPanel, BoxLayout.Y_AXIS));
        dataMenuPanel.setPreferredSize(new Dimension(150, getHeight()));
        dataMenuPanel.setBackground(new Color(255, 165, 0)); // Couleur orange
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        dataMenuPanel.setBorder(BorderFactory.createCompoundBorder(blackBorder, new EmptyBorder(10, 10, 10, 10)));

        JButton salesButton = createStyledButton("Sales");
        salesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSalesData();
            }
        });
        dataMenuPanel.add(salesButton);

        dataMenuPanel.add(Box.createVerticalStrut(10)); // Espacement réduit

        JButton reservationsButton = createStyledButton("Reservations");
        reservationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showReservationsData();
            }
        });
        dataMenuPanel.add(reservationsButton);
        dataMenuPanel.add(Box.createVerticalStrut(10)); 

        JButton activitiesButton = createStyledButton("Latest Activities");
        activitiesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLatestActivities();
            }
        });
        dataMenuPanel.add(activitiesButton);

        mainPanel.add(dataMenuPanel, BorderLayout.WEST);

        tabbedPane = new JTabbedPane();

        // Création de l'onglet pour le graphique de ventes
        JPanel salesChartPanel = new JPanel(new BorderLayout());
        JFreeChart salesChart = createSalesChart();
        ChartPanel chartPanel = new ChartPanel(salesChart);
        salesChartPanel.add(chartPanel, BorderLayout.CENTER);
        tabbedPane.addTab("Sales Chart", salesChartPanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Ajout du bouton Imprimer
        JButton printButton = new JButton("Imprimer");
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printToPDF();
            }
        });
        mainPanel.add(printButton, BorderLayout.SOUTH);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
        button.setBackground(new Color(240, 240, 240)); // Couleur de fond légèrement plus claire
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Bordure noire
        return button;
    }

    private void showSalesData() {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vente");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel();
            JTable table = new JTable(model);
            model.addColumn("Vente ID");
            model.addColumn("Trajet ID");
            model.addColumn("Date Vente");
            model.addColumn("Montant");

            while (resultSet.next()) {
                int venteId = resultSet.getInt("vente_id");
                int trajetId = resultSet.getInt("trajet_id");
                Date dateVente = resultSet.getDate("date_vente");
                double montant = resultSet.getDouble("montant");
                model.addRow(new Object[]{venteId, trajetId, dateVente, montant});
            }

            JScrollPane scrollPane = new JScrollPane(table);
            JPanel salesPanel = new JPanel(new BorderLayout());
            salesPanel.setBorder(BorderFactory.createTitledBorder("Sales Data")); // Ajouter un titre
            salesPanel.add(scrollPane, BorderLayout.CENTER);
            tabbedPane.addTab("Sales Data", salesPanel);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while fetching sales data. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }

    private void showReservationsData() {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reservation");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel();
            JTable table = new JTable(model);
            model.addColumn("Reservation ID");
            model.addColumn("Trajet ID");
            model.addColumn("Date Reservation");
            model.addColumn("Nom Client");

            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservation_id");
                int trajetId = resultSet.getInt("trajet_id");
                Date dateReservation = resultSet.getDate("date_reservation");
                String nomClient = resultSet.getString("nom_client");
                model.addRow(new Object[]{reservationId, trajetId, dateReservation, nomClient});
            }

            JScrollPane scrollPane = new JScrollPane(table);
            JPanel reservationsPanel = new JPanel(new BorderLayout());
            reservationsPanel.setBorder(BorderFactory.createTitledBorder("Reservations Data")); // Ajouter un titre
            reservationsPanel.add(scrollPane, BorderLayout.CENTER);
            tabbedPane.addTab("Reservations Data", reservationsPanel);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while fetching reservations data. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }

    private JFreeChart createSalesChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT date_vente, SUM(montant) AS total FROM vente GROUP BY date_vente");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String date = resultSet.getString("date_vente");
                double total = resultSet.getDouble("total");
                dataset.addValue(total, "Sales", date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while fetching data for sales chart. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return ChartFactory.createLineChart("Sales Chart", "Date", "Total", dataset);
    }

    private void printToPDF() {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Ajouter le graphique de ventes
            JFreeChart salesChart = createSalesChart();
            BufferedImage bufferedImage = salesChart.createBufferedImage(400, 300);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            baos.close();
            PDImageXObject chartImage = PDImageXObject.createFromByteArray(document, imageBytes, null);
            contentStream.drawImage(chartImage, 100, 500, 400, 300);

            // Ajouter les tables de ventes et de réservations
            List<String> salesData = fetchTableData("vente");
            List<String> reservationsData = fetchTableData("reservation");

            addTableToPDF(contentStream, salesData, 100, 400);
            addTableToPDF(contentStream, reservationsData, 100, 200);

            contentStream.close();

            String fileName = "dashboard_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".pdf";
            document.save(fileName);
            document.close();

            JOptionPane.showMessageDialog(this, "PDF créé avec succès : " + fileName, "Success", JOptionPane.INFORMATION_MESSAGE);
            File file = new File(fileName);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while generating PDF. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<String> fetchTableData(String tableName) {
        List<String> data = new ArrayList<>();
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            StringBuilder header = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                header.append(metaData.getColumnName(i)).append("\t");
            }
            data.add(header.toString());

            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(resultSet.getString(i)).append("\t");
                }
                data.add(row.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while fetching data for PDF. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }

    private void addTableToPDF(PDPageContentStream contentStream, List<String> data, float x, float y) throws IOException {
        PDFont font = PDType1Font.HELVETICA_BOLD;
        int fontSize = 10;
        float leading = 1.5f * fontSize;

        for (String line : data) {
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.newLineAtOffset(x, y);
            for (String part : line.split("\t")) {
                contentStream.showText(part);
                contentStream.newLineAtOffset(100, 0);
            }
            contentStream.endText();
            y -= leading;
        }
    }

    private void showLatestActivities() {
        try (Connection connection = DbConnector.getConnection()) {
            JPanel latestActivitiesPanel = new JPanel(new BorderLayout());

            JTextArea latestActivitiesTextArea = new JTextArea();
            latestActivitiesTextArea.setEditable(false); // Rend le JTextArea non éditable

            // Récupérer les dernières activités de vente
            StringBuilder latestActivities = new StringBuilder();
            String selectSalesQuery = "SELECT * FROM vente ORDER BY date_vente DESC LIMIT 3";
            try (PreparedStatement salesStatement = connection.prepareStatement(selectSalesQuery);
                 ResultSet salesResultSet = salesStatement.executeQuery()) {

                latestActivities.append("Dernières activités de vente :\n");
                while (salesResultSet.next()) {
                    int venteId = salesResultSet.getInt("vente_id");
                    int trajetId = salesResultSet.getInt("trajet_id");
                    Date dateVente = salesResultSet.getDate("date_vente");
                    double montant = salesResultSet.getDouble("montant");
                    latestActivities.append("Vente ID: ").append(venteId).append(", Trajet ID: ").append(trajetId)
                            .append(", Date Vente: ").append(dateVente).append(", Montant: ").append(montant).append("\n");
                }
            }

            // Récupérer les dernières activités de réservation
            String selectReservationsQuery = "SELECT * FROM reservation ORDER BY date_reservation DESC LIMIT 3";
            try (PreparedStatement reservationsStatement = connection.prepareStatement(selectReservationsQuery);
                 ResultSet reservationsResultSet = reservationsStatement.executeQuery()) {

                latestActivities.append("\nDernières activités de réservation :\n");
                while (reservationsResultSet.next()) {
                    int reservationId = reservationsResultSet.getInt("reservation_id");
                    int trajetId = reservationsResultSet.getInt("trajet_id");
                    Date dateReservation = reservationsResultSet.getDate("date_reservation");
                    String nomClient = reservationsResultSet.getString("nom_client");
                    latestActivities.append("Reservation ID: ").append(reservationId).append(", Trajet ID: ").append(trajetId)
                            .append(", Date Reservation: ").append(dateReservation).append(", Nom Client: ").append(nomClient).append("\n");
                }
            }

            latestActivitiesTextArea.setText(latestActivities.toString());
            JScrollPane scrollPane = new JScrollPane(latestActivitiesTextArea); // Ajoute une barre de défilement si nécessaire
            latestActivitiesPanel.add(scrollPane, BorderLayout.CENTER);

            tabbedPane.addTab("Latest Activities", latestActivitiesPanel);
            tabbedPane.setSelectedComponent(latestActivitiesPanel);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while fetching latest activities. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }
}


