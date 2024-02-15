package voyageur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

public class ReservationInterface extends JFrame {
    private JComboBox<String> gareDepartComboBox;
    private String gareDepart;

    private JComboBox<String> gareArriveeComboBox;
    private String gareArrivee;

    private int nombreAdultes;
    private int nombreEnfants;
    public Reservation reservation;

    public ReservationInterface() {
        reservation = new Reservation();

        JPanel content_global = new JPanel();
        content_global.setLayout(new GridBagLayout());
        content_global.setBackground(new Color(239, 46, 65));

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(Color.WHITE);

        JPanel content1 = new JPanel();
        content1.setLayout(new BorderLayout());
        content1.setBackground(Color.WHITE);

        JPanel oncf1 = new JPanel();
        oncf1.setLayout(new GridLayout(4, 1));
        oncf1.setBackground(Color.WHITE);
        JLabel n1 = new JLabel("Ma gare de départ");
        n1.setForeground(new Color(74, 32, 170));
        List<String> gares = new ArrayList<>();
        gares.add("ADDAKHLA");
        gares.add("AEROPORT MED V");
        gares.add("AGADIR (SUPRAT.)");
        gares.add("AGDZ (SUPRAT.)");
        gares.add("AIN DEFALI");
        gares.add("AIN SBIT");
        gares.add("AIN SEBAA");
        gares.add("AIN-TAOUJDATE");
        gares.add("AL AKBA LHAMRA");
        gares.add("ASILAH");
        gares.add("ASSA");
        gares.add("AZEMMOUR HALTE");
        gares.add("AZROU (SUPRAT.)");
        gares.add("BENGUERIR");
        gares.add("BENI NSAR PORT");
        gares.add("BENI NSAR VILLE");
        gares.add("BENI-MELLAL");
        gares.add("BENI-OUKL");
        gares.add("BERRECHID");
        gares.add("BIDANE");
        gares.add("BOUIZAKARNE");
        gares.add("BOUJDOUR");
        gares.add("BOUMALNE");
        gares.add("BOUSKOURA");
        gares.add("BOUZNIKA");
        gares.add("CASA PORT");
        gares.add("CASA VOYAGEURS");
        gares.add("CHEBABAT");
        gares.add("CHEFCHAOUEN");
        gares.add("CASA OASIS");
        gares.add("DALIA");
        gares.add("DAR EL GUEDARI");
        gares.add("RABAT AGDAL");
        gares.add("RABAT VILLE");
        gares.add("SETTAT");

        gareDepartComboBox = new JComboBox<>(gares.toArray(new String[0]));
        gareDepartComboBox.setPreferredSize(new Dimension(270, 40));

        gareDepartComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gareDepart = (String) gareDepartComboBox.getSelectedItem();
            }
        });



     

        JLabel n2 = new JLabel("Ma gare d'arrivée");
        n2.setForeground(new Color(74, 32, 170));
        gareArriveeComboBox = new JComboBox<>(gares.toArray(new String[0]));
        gareArriveeComboBox.setPreferredSize(new Dimension(270, 40));
        gareArriveeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gareArrivee = (String) gareArriveeComboBox.getSelectedItem();
            }
        });

        oncf1.add(n1);
        oncf1.add(gareDepartComboBox);
        oncf1.add(n2);
        oncf1.add(gareArriveeComboBox);
        content1.add(oncf1, BorderLayout.WEST);

        JPanel oncf2 = new JPanel();
        oncf2.setLayout(new GridLayout(4, 1));
        oncf2.setBackground(Color.WHITE);

        JLabel n3 = new JLabel("Ma date de départ");
        n3.setForeground(new Color(74, 32, 170));

        JDateChooser dateDepartChooser = new JDateChooser();
        dateDepartChooser.setPreferredSize(new Dimension(270, 40));
        Date dateAujourdhui = new Date();
        dateDepartChooser.setDate(dateAujourdhui);

        JLabel n4 = new JLabel("Ma date de retour");
        n4.setForeground(new Color(74, 32, 170));
        JDateChooser dateRetourChooser = new JDateChooser();
        dateRetourChooser.setPreferredSize(new Dimension(270, 40));

        oncf2.add(n3);
        oncf2.add(dateDepartChooser);
        oncf2.add(n4);
        oncf2.add(dateRetourChooser);
        content1.add(oncf2, BorderLayout.CENTER);

        JPanel oncf3 = new JPanel();
        oncf3.setLayout(new BorderLayout());
        oncf3.setBackground(Color.WHITE);

        JPanel oncf31 = new JPanel();
        oncf31.setLayout(new GridLayout(2, 1));
        oncf31.setBackground(Color.WHITE);

        JLabel n5 = new JLabel("Voyageurs");
        n5.setForeground(new Color(74, 32, 170));
        JButton z5 = new JButton("1 Adulte");
        z5.setPreferredSize(new Dimension(270, 40));
        z5.setForeground(new Color(74, 32, 170));

        z5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPopupMenu popupMenu = new JPopupMenu();
                String[] options1 = {"1", "2", "3", "4", "5", "6"};
                String[] options2 = {"0", "1", "2", "3", "4"};
                JComboBox<String> adultesComboBox = new JComboBox<>(options1);
                JComboBox<String> enfantsComboBox = new JComboBox<>(options2);
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(3, 2));
                JLabel adultesLabel = new JLabel("Nombre d'adultes:");
                JLabel enfantsLabel = new JLabel("Nombre d'enfants:");
                panel.add(adultesLabel);
                panel.add(adultesComboBox);
                panel.add(enfantsLabel);
                panel.add(enfantsComboBox);
                popupMenu.add(panel);
                int result = JOptionPane.showConfirmDialog(null, panel, "Sélectionnez le nombre d'adultes et d'enfants",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    nombreAdultes = Integer.parseInt((String) adultesComboBox.getSelectedItem());
                    nombreEnfants = Integer.parseInt((String) enfantsComboBox.getSelectedItem());
                    z5.setText(nombreAdultes + " Adulte(s), " + nombreEnfants + " Enfant(s)");
                }
            }
        });

        oncf31.add(n5);
        oncf31.add(z5);
        oncf3.add(oncf31, BorderLayout.NORTH);

        JPanel oncf32 = new JPanel();
        oncf32.setLayout(new GridLayout(2, 1));
        oncf32.setBackground(Color.WHITE);

        JLabel n6 = new JLabel("Mon confort");
        n6.setForeground(new Color(74, 32, 170));
        oncf32.add(n6);
        JPanel oncf321 = new JPanel();
        oncf321.setLayout(new FlowLayout());
        oncf321.setBackground(Color.WHITE);
        ButtonGroup group = new ButtonGroup();

        CustomRadioButton b1 = new CustomRadioButton("1ère classe", false);
        b1.setPreferredSize(new Dimension(90, 40));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String confortSelectionne = b1.getText();
                reservation.setConfortSelectionne(confortSelectionne);
            }
        });

        CustomRadioButton b2 = new CustomRadioButton("2ème classe", true);
        b2.setPreferredSize(new Dimension(95, 40));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String confortSelectionne = b2.getText();
                reservation.setConfortSelectionne(confortSelectionne);
            }
        });

        CustomRadioButton b3 = new CustomRadioButton("Lit Single", false);
        b3.setPreferredSize(new Dimension(90, 40));
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String confortSelectionne = b3.getText();
                reservation.setConfortSelectionne(confortSelectionne);
            }
        });
        group.add(b1);
        group.add(b2);
        group.add(b3);

        oncf321.add(b1);
        oncf321.add(b2);
        oncf321.add(b3);
        oncf32.add(oncf321);
        oncf3.add(oncf32, BorderLayout.SOUTH);

        content1.add(oncf3, BorderLayout.EAST);

        oncf1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        oncf2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        oncf3.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.setBackground(Color.WHITE);
        JButton b4 = new JButton("Rechercher");
        b4.setPreferredSize(new Dimension(250, 40));
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gareDepart == null || gareArrivee == null) {
                    JOptionPane.showMessageDialog(null, "Les champs 'Gare de départ' et 'Gare d'arrivée' sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Date dateDepart = dateDepartChooser.getDate();
                Date dateRetour = dateRetourChooser.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDateDepart = sdf.format(dateDepart);

                reservation.setGareDepart(gareDepart);
                reservation.setGareArrivee(gareArrivee);
                reservation.setDateDepart(formattedDateDepart);

                if (dateRetour != null) {
                    String formattedDateRetour = sdf.format(dateRetour);
                    reservation.setDateRetour(formattedDateRetour);
                }

                reservation.setQuantiteAdultes(nombreAdultes);
                reservation.setQuantiteEnfants(nombreEnfants);

                if (reservation.getConfortSelectionne() == null) {
                    reservation.setConfortSelectionne("2ème classe");
                }

                reservation.reserverBillet();

                b4.setForeground(Color.WHITE);
                b4.setBackground(new Color(74, 32, 170));

                List<String> listeTrajets = reservation.rechercherTrajets();
                ResultatsTrajetsPanel.afficherResultats(listeTrajets);
            }
        });

        b4.setForeground(new Color(74, 32, 170));
        b4.setBackground(new Color(229, 233, 241));

        buttons.add(b4);
        content.add(buttons, BorderLayout.SOUTH);
        content.add(content1, BorderLayout.CENTER);

        JLabel labelTexte = new JLabel("ONCF Voyages");
        labelTexte.setFont(new Font("Arial", Font.BOLD, 30));
        labelTexte.setForeground(Color.WHITE);

        GridBagConstraints gbcTexte = new GridBagConstraints();
        gbcTexte.gridx = 0;
        gbcTexte.gridy = 0;
        gbcTexte.anchor = GridBagConstraints.NORTHWEST;
        gbcTexte.insets = new Insets(40, 50, 10, 10);
        content_global.add(labelTexte, gbcTexte);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        content_global.add(content, gbc);
        setTitle("ONCF - Achat de billet de train");
        setContentPane(content_global);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1200, 600);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ReservationInterface();
            }
        });
    }
}

class CustomRadioButton extends JRadioButton {
    private boolean isSelectedByDefault;

    public CustomRadioButton(String text, boolean isSelectedByDefault) {
        super(text);
        this.isSelectedByDefault = isSelectedByDefault;

        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(true);
        setHorizontalAlignment(SwingConstants.LEFT);

        if (isSelectedByDefault) {
            setSelected(true);
            setBackground(new Color(74, 32, 170));
            setForeground(Color.WHITE);
        } else {
            setBackground(Color.WHITE);
            setForeground(new Color(74, 32, 170));
        }

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isSelected()) {
            g.setColor(new Color(74, 32, 170));
            setForeground(Color.WHITE);
        } else {
            g.setColor(Color.WHITE);
            setForeground(new Color(74, 32, 170));
        }

        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(getForeground());
        g.drawString(getText(), 15, getHeight() - 15);

        g.setColor(Color.BLUE);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
