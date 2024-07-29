package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DatabaseUtil;

public class AdaugaClientPanel extends JPanel {
    private JTextField numeField;
    private JTextField adresaField;
    private JTextField nrTelefonField;
    private JTextField emailField;
    private JFrame parentFrame;

    public AdaugaClientPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Adăugarea câmpurilor de text
        add(new JLabel("Nume:"), gbc);
        gbc.gridx++;
        numeField = new JTextField(20);
        add(numeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Adresa:"), gbc);
        gbc.gridx++;
        adresaField = new JTextField(20);
        add(adresaField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Nr. Telefon:"), gbc);
        gbc.gridx++;
        nrTelefonField = new JTextField(20);
        add(nrTelefonField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Email:"), gbc);
        gbc.gridx++;
        emailField = new JTextField(20);
        add(emailField, gbc);

        // Buton Adauga Client
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton addButton = new JButton("Adaugă Client");
        add(addButton, gbc);

        // Buton Back
        gbc.gridy++;
        JButton backButton = new JButton("Back");
        add(backButton, gbc);

        // Acțiune buton Adauga Client
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = numeField.getText();
                String adresa = adresaField.getText();
                String nrTelefon = nrTelefonField.getText();
                String email = emailField.getText();
                adaugaClient(nume, adresa, nrTelefon, email);
            }
        });

        // Acțiune buton Back
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new ClientPanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
    }

    private void adaugaClient(String nume, String adresa, String nrTelefon, String email) {
        String query = "INSERT INTO client (nume, adresa, nrTelefon, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nume);
            stmt.setString(2, adresa);
            stmt.setString(3, nrTelefon);
            stmt.setString(4, email);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Client adăugat cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la adăugarea clientului.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}
