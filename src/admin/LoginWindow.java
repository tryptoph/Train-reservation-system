package admin;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.lang.reflect.*;

public class LoginWindow extends JFrame {
    private JButton adminButton;
    private JButton userButton;

    private static class DbConnector {
        private static final String JDBC_URL = "jdbc:mysql://localhost:3306/train";
        private static final String USER = "root";
        private static final String PASSWORD = "ONCF@2024@ONCF";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        }
    }

    public LoginWindow() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null); 

        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(239, 46, 65)); 

        JLabel oncfLabel = new JLabel("ONCF", SwingConstants.CENTER);
        oncfLabel.setFont(new Font("Arial", Font.BOLD, 50)); 
        oncfLabel.setForeground(Color.WHITE);

        adminButton = new JButton("Admin");
        adminButton.setFocusable(false); 
        adminButton.setBackground(Color.WHITE);
        adminButton.setForeground(new Color(239, 46, 65));
        adminButton.setFont(new Font("Arial", Font.BOLD, 24)); 
        adminButton.setBorder(new LineBorder(new Color(239, 46, 65), 2)); 
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAdminLoginWindow();
            }
        });

        userButton = new JButton("User");
        userButton.setFocusable(false); 
        userButton.setBackground(Color.WHITE);
        userButton.setForeground(new Color(239, 46, 65));
        userButton.setFont(new Font("Arial", Font.BOLD, 24)); 
        userButton.setBorder(new LineBorder(new Color(239, 46, 65), 2)); 
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	openWindow("voyageurs.ReservationsInterface");
            }
        });

        mainPanel.add(oncfLabel);
        mainPanel.add(adminButton);
        mainPanel.add(userButton);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void openAdminLoginWindow() {
        AdminLoginWindow adminLoginWindow = new AdminLoginWindow();
        adminLoginWindow.setVisible(true);
    }
    
    private void openWindow(String className) {
        try {
            Class<?> windowClass = Class.forName(className);
            Constructor<?> constructor = windowClass.getConstructor();
            JFrame window = (JFrame) constructor.newInstance();
            window.setVisible(true);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.setVisible(true);
            }
        });
    }
}

class AdminLoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private boolean validateAdminLogin(String username, String password) {
        try (Connection connection = DbConnector.getConnection()) {
            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public AdminLoginWindow() {
        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setSize(400, 300);
        setLocationRelativeTo(null); 

        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(239, 46, 65)); 

        Font labelFont = new Font("Arial", Font.BOLD, 20);
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);

        Font textFieldFont = new Font("Arial", Font.PLAIN, 18);
        usernameField = new JTextField();
        usernameField.setFont(textFieldFont);
        passwordField = new JPasswordField();
        passwordField.setFont(textFieldFont);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20)); 
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (validateAdminLogin(username, password)) {
                    openMainGUI();
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void openMainGUI() {
        MainGUI mainGUI = new MainGUI();
        mainGUI.setVisible(true);
    }
}

class UserClass extends JFrame {
    public UserClass() {
        setTitle("User Window");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setSize(400, 300);
        setLocationRelativeTo(null); 
    }
}
