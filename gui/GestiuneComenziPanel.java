package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DatabaseUtil;
import util.GenerateReport;

public class GestiuneComenziPanel extends JPanel {
    private JTextField numeClientField;
    private JTextField numeLivratorField;
    private JTextField detaliiProdusField;
    private JTextField cantitateField;
    private JTextField pretField;
    private JTextField statusComandaField;
    private JFrame parentFrame;

    public GestiuneComenziPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Logo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel logoLabel = new JLabel(resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/comenzi.png"), 150, 150));
        add(logoLabel, gbc);

        // Nume Client
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(new JLabel("Nume Client:"), gbc);
        gbc.gridx = 1;
        numeClientField = new JTextField(20);
        add(numeClientField, gbc);

        // Nume Livrator
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Nume Livrator:"), gbc);
        gbc.gridx = 1;
        numeLivratorField = new JTextField(20);
        add(numeLivratorField, gbc);

        // Detalii Produs
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Detalii Produs:"), gbc);
        gbc.gridx = 1;
        detaliiProdusField = new JTextField(20);
        add(detaliiProdusField, gbc);

        // Cantitate
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Cantitate:"), gbc);
        gbc.gridx = 1;
        cantitateField = new JTextField(20);
        add(cantitateField, gbc);

        // Pret
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Pret:"), gbc);
        gbc.gridx = 1;
        pretField = new JTextField(20);
        add(pretField, gbc);

        // Status Comanda
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Status Comanda:"), gbc);
        gbc.gridx = 1;
        statusComandaField = new JTextField(20);
        add(statusComandaField, gbc);

        // Buton Salveaza Comanda
        gbc.gridy++;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        JButton saveButton = new JButton("Salvează Comanda");
        add(saveButton, gbc);

        // Buton Genereaza Raport PDF
        gbc.gridy++;
        JButton generatePdfButton = new JButton("Generează Raport PDF");
        add(generatePdfButton, gbc);

        // Buton Back
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton backButton = new JButton("Back");
        add(backButton, gbc);

        // Acțiune buton Salvează Comanda
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeClient = numeClientField.getText();
                String numeLivrator = numeLivratorField.getText();
                String detaliiProdus = detaliiProdusField.getText();
                String cantitate = cantitateField.getText();
                String pret = pretField.getText();
                String statusComanda = statusComandaField.getText();
                salveazaComanda(numeClient, numeLivrator, detaliiProdus, cantitate, pret, statusComanda);
            }
        });

        // Acțiune buton Generează Raport PDF
        generatePdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerateReport.generatePdfReport();
                JOptionPane.showMessageDialog(parentFrame, "Raportul PDF a fost generat cu succes!");
            }
        });

        // Acțiune buton Back
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new MeniuPrincipal(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
    }

    private void salveazaComanda(String numeClient, String numeLivrator, String detaliiProdus, String cantitate, String pret, String statusComanda) {
        String query = "INSERT INTO Comanda (nume_client, nume_livrator, detalii_produs, cantitate, pret, status_comanda) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, numeClient);
            stmt.setString(2, numeLivrator);
            stmt.setString(3, detaliiProdus);
            stmt.setInt(4, Integer.parseInt(cantitate));
            stmt.setDouble(5, Double.parseDouble(pret));
            stmt.setString(6, statusComanda);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Comanda a fost salvată cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la salvarea comenzii.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
