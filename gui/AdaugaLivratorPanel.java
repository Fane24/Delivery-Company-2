package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DatabaseUtil;

public class AdaugaLivratorPanel extends JPanel {
    private JTextField numeField;
    private JTextField prenumeField;
    private JTextField telefonField;
    private JTextField vehiculField;
    private JFrame parentFrame;

    public AdaugaLivratorPanel(JFrame parentFrame) {
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
        add(new JLabel("Prenume:"), gbc);
        gbc.gridx++;
        prenumeField = new JTextField(20);
        add(prenumeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Telefon:"), gbc);
        gbc.gridx++;
        telefonField = new JTextField(20);
        add(telefonField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Vehicul:"), gbc);
        gbc.gridx++;
        vehiculField = new JTextField(20);
        add(vehiculField, gbc);

        // Buton Adauga Livrator
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton addButton = new JButton("Adaugă Livrator");
        add(addButton, gbc);

        // Buton Back
        gbc.gridy++;
        JButton backButton = new JButton("Back");
        add(backButton, gbc);

        // Acțiune buton Adauga Livrator
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = numeField.getText();
                String prenume = prenumeField.getText();
                String telefon = telefonField.getText();
                String vehicul = vehiculField.getText();
                adaugaLivrator(nume, prenume, telefon, vehicul);
            }
        });

        // Acțiune buton Back
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new LivratorPanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
    }

    private void adaugaLivrator(String nume, String prenume, String telefon, String vehicul) {
        String query = "INSERT INTO livrator (nume, prenume, telefon, vehicul) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nume);
            stmt.setString(2, prenume);
            stmt.setString(3, telefon);
            stmt.setString(4, vehicul);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Livrator adăugat cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la adăugarea livratorului.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}
