package admin;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginWindow extends JFrame {
    private JButton adminButton;
    private JButton userButton;

    public LoginWindow() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(239, 46, 65)); // Red-like coloration

        JLabel oncfLabel = new JLabel("ONCF", SwingConstants.CENTER);
        oncfLabel.setFont(new Font("Arial", Font.BOLD, 50)); // Larger font size
        oncfLabel.setForeground(Color.WHITE);

        adminButton = new JButton("Admin");
        adminButton.setFocusable(false); // Disable focus styling
        adminButton.setBackground(Color.WHITE);
        adminButton.setForeground(new Color(239, 46, 65));
        adminButton.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font size
        adminButton.setBorder(new LineBorder(new Color(239, 46, 65), 2)); // Add border
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAdminLoginWindow();
            }
        });

        userButton = new JButton("User");
        userButton.setFocusable(false); // Disable focus styling
        userButton.setBackground(Color.WHITE);
        userButton.setForeground(new Color(239, 46, 65));
        userButton.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font size
        userButton.setBorder(new LineBorder(new Color(239, 46, 65), 2)); // Add border
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUserWindow();
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

    private void openUserWindow() {
        UserClass userClass = new UserClass();
        userClass.setVisible(true);
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

    public AdminLoginWindow() {
        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set to dispose on close
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(239, 46, 65)); // Red-like coloration

        // Increase font size for labels
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);

        // Increase font size for text fields
        Font textFieldFont = new Font("Arial", Font.PLAIN, 18);
        usernameField = new JTextField();
        usernameField.setFont(textFieldFont);
        passwordField = new JPasswordField();
        passwordField.setFont(textFieldFont);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20)); // Larger font size for the button label
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (validateAdminLogin(username, password)) {
                    openMainGUI();
                    dispose(); // Close the admin login window
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

    private boolean validateAdminLogin(String username, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/train", "root", "Hwxq9540!")) {
            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // If a row exists, the credentials are valid
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void openMainGUI() {
        MainGUI mainGUI = new MainGUI();
        mainGUI.setVisible(true);
    }
}

class UserClass extends JFrame {
    public UserClass() {
        setTitle("User Window");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set to dispose on close
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window on the screen

        // Add components and functionality specific to the user window here
    }
}
