package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DatabaseUtil;

public class AdaugaRutaPanel extends JPanel {
    private JTextField traseuField;
    private JTextField nrKmField;
    private JTextField numeLivratorField;
    private JTextField numarOpririField;
    private JFrame parentFrame;

    public AdaugaRutaPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Adăugarea câmpurilor de text
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Traseu:"), gbc);
        gbc.gridx = 1;
        traseuField = new JTextField(20);
        add(traseuField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("KM:"), gbc);
        gbc.gridx = 1;
        nrKmField = new JTextField(20);
        add(nrKmField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Nume Livrator:"), gbc);
        gbc.gridx = 1;
        numeLivratorField = new JTextField(20);
        add(numeLivratorField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Numar Opriri:"), gbc);
        gbc.gridx = 1;
        numarOpririField = new JTextField(20);
        add(numarOpririField, gbc);

        // Buton Adauga Ruta
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton addButton = new JButton("Adaugă Ruta");
        add(addButton, gbc);

        // Buton Back
        gbc.gridy++;
        JButton backButton = new JButton("Back");
        add(backButton, gbc);

        // Acțiune buton Adauga Ruta
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String traseu = traseuField.getText();
                String nrKm = nrKmField.getText();
                String numeLivrator = numeLivratorField.getText();
                String numarOpriri = numarOpririField.getText();
                adaugaRuta(traseu, nrKm, numeLivrator, numarOpriri);
            }
        });

        // Acțiune buton Back
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new RutePanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
    }

    private void adaugaRuta(String traseu, String nrKm, String numeLivrator, String numarOpriri) {
        String query = "INSERT INTO ruta (traseu, nr_km, nume_livrator, numar_opriri) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, traseu);
            stmt.setString(2, nrKm);
            stmt.setString(3, numeLivrator);
            stmt.setString(4, numarOpriri);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Ruta adăugată cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la adăugarea rutei.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}
