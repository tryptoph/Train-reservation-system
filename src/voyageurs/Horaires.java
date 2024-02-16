package voyageurs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

public class Horaires extends JFrame {
	private JComboBox<String> gareDepartComboBox;
	private String gareDepart;
	
	private JComboBox<String> gareArriveeComboBox;
	private String gareArrivee;
    //private JTextField gareArriveeField;
    
    private int nombreAdultes;  
    private int nombreEnfants;
    //private String confortSelectionne;
    private Reservations reservation;
        
    public Horaires() {
    	// Créez une instance de Reservation 
        reservation = new Reservations();
        
        JPanel content_global = new JPanel();
        content_global.setLayout(new GridBagLayout());
        content_global.setBackground(new Color(239, 46, 65));
        
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(Color.WHITE);
       
        JPanel content1 = new JPanel();
        content1.setLayout(new BorderLayout());
        content1.setBackground(Color.WHITE);
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Créez les boutons CustomRadioButton
        CustomRadioButton button1 = new CustomRadioButton("J'achète mon billet", false);
        button1.setPreferredSize(new Dimension(150, 40));
        CustomRadioButton button2 = new CustomRadioButton("Je consulte les horaires", true);
        button2.setPreferredSize(new Dimension(160, 40));
        CustomRadioButton button3 = new CustomRadioButton("J'utilise mon tarif réduit", false);
        button3.setPreferredSize(new Dimension(160, 40));

        button1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             // Instancier et afficher la première page
        	 AchatBillet page1 = new AchatBillet();
             page1.setVisible(true);
         }
     });
        
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Instancier et afficher la première page
                Horaires page2 = new Horaires();
                page2.setVisible(true);
            }
        });
        
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Instancier et afficher la première page
            	CarteReduction page3 = new CarteReduction();
                page3.setVisible(true);
            }
        });
        // Ajoutez les boutons au panneau
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);
        
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
        gares.add("Mohammedia");
        gares.add("Rabat");
        gares.add("Taza");
        gares.add("RABAT AGDAL");
        gares.add("RABAT VILLE");
        gares.add("SETTAT");
        

        
        // Créez la JComboBox avec les gares
        gareDepartComboBox = new JComboBox<>(gares.toArray(new String[0]));
        gareDepartComboBox.setPreferredSize(new Dimension(270, 40));

        
        
        gareDepartComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mettez à jour la variable avec la valeur sélectionnée
            	gareDepart = (String) gareDepartComboBox.getSelectedItem();
            }
        });

        //gareDepartField = new JTextField(23);
        JLabel n2 = new JLabel("Ma gare d'arrivée");
        n2.setForeground(new Color(74, 32, 170));
        //gareArriveeField = new JTextField(23);
        gareArriveeComboBox = new JComboBox<>(gares.toArray(new String[0]));
        gareArriveeComboBox.setPreferredSize(new Dimension(270, 40));
        gareArriveeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mettez à jour la variable avec la valeur sélectionnée
            	gareArrivee = (String) gareArriveeComboBox.getSelectedItem();
            }
        });
        oncf1.add(n1);
        oncf1.add(gareDepartComboBox);
        //oncf1.add(gareDepartField);
        oncf1.add(n2);
        //oncf1.add(gareArriveeField);
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

        oncf1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 
        oncf2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.setBackground(Color.WHITE);
        JButton b4 = new JButton("Rechercher");
        b4.setPreferredSize(new Dimension(250, 40));


        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
            	// Vérifier si les gares de départ et d'arrivée sont saisies
                if (gareDepart == null || gareArrivee == null) {
                    JOptionPane.showMessageDialog(null, "Les champs 'Gare de départ' et 'Gare d'arrivée' sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Date dateDepart = dateDepartChooser.getDate();
                Date dateRetour = dateRetourChooser.getDate();
     
             // Utilisez SimpleDateFormat pour formater les dates
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
                
                // Si le confort n'est pas saisi, utilisez "2ème classe" par défaut
                if (reservation.getConfortSelectionne() == null) {
                    reservation.setConfortSelectionne("2ème classe");
                }
                
                // Appeler la méthode de réservation
                reservation.reserverBillet();
                
                b4.setForeground(Color.WHITE); 
                b4.setBackground(new Color(74, 32, 170)); 
                
                // Appel de la nouvelle méthode de recherche des trajets
                List<String> listeTrajets = reservation.rechercherTrajets();

                // Affichez les résultats dans une nouvelle fenêtre à l'aide de ResultatsTrajetsPanel
                CardHoraires.afficherResultats(gareDepart,gareArrivee);
            }
            
        });
        
        b4.setForeground(new Color(74, 32, 170));
        b4.setBackground(new Color(229, 233, 241));
        
        buttons.add(b4);
        content.add(buttonsPanel, BorderLayout.NORTH);
        content.add(buttons, BorderLayout.SOUTH);
        content.add(content1, BorderLayout.CENTER);
        
        JLabel labelTexte = new JLabel("ONCF Voyages");
        labelTexte.setFont(new Font("Arial", Font.BOLD, 30)); 
        labelTexte.setForeground(Color.WHITE);

        GridBagConstraints gbcTexte = new GridBagConstraints();
        gbcTexte.gridx = 0;
        gbcTexte.gridy = 0;
        gbcTexte.anchor = GridBagConstraints.NORTHWEST;
        gbcTexte.insets = new Insets(40, 50, 10, 10); // Marge
        content_global.add(labelTexte, gbcTexte);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        content_global.add(content, gbc);
        setTitle("ONCF - Achat de billet de train");
        setContentPane(content_global);
        //pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1200,600);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Horaires();
            }
        });
    }
    
    public class CustomRadioButton extends JRadioButton {
    	private boolean isSelectedByDefault;
        public CustomRadioButton(String text,  boolean isSelectedByDefault) {
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
                //g.setColor(getBackground());
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
	
}


