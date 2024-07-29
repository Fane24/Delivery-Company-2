package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DatabaseUtil;

public class LoginFrame extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JFrame parentFrame;

    public LoginFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Adăugarea logo-ului
        JLabel logoLabel = new JLabel(resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/logo.png"), 200, 100));
        add(logoLabel, gbc);

        // Titlu
        gbc.gridy++;
        JLabel titleLabel = new JLabel("SC DELIVERY RO");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(128, 0, 128));
        add(titleLabel, gbc);

        // Iconiță utilizator și câmp text
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel userIcon = new JLabel(resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/clienti.png"), 30, 30));
        add(userIcon, gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        add(usernameField, gbc);

        // Iconiță parolă și câmp parolă
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel passwordIcon = new JLabel(resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/parola.png"), 30, 30));
        add(passwordIcon, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

        // Butonul de login
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("LOG IN");
        add(loginButton, gbc);

        // Acțiune buton
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login reușit!");
                    parentFrame.dispose();  // Închide fereastra de login
                    JFrame mainFrame = new JFrame("SC DELIVERY RO - Meniu Principal");
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mainFrame.setContentPane(new MeniuPrincipal(mainFrame));
                    mainFrame.setSize(800, 600);
                    mainFrame.setLocationRelativeTo(null);
                    mainFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "User sau Parola incorecta te rugam sa mai incerci", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
