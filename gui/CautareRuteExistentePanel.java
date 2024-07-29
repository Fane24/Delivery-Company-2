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

public class CautareRuteExistentePanel extends JPanel {
    private JTextField searchField;
    private JTextArea resultArea;
    private JFrame parentFrame;

    public CautareRuteExistentePanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Adăugarea iconiței
        JLabel iconLabel = new JLabel(resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/rute.png"), 150, 150));
        add(iconLabel, gbc);

        // Căsuță de căutare
        gbc.gridy++;
        gbc.gridwidth = 1;
        searchField = new JTextField(20);
        add(searchField, gbc);

        gbc.gridx++;
        JButton searchButton = new JButton("Caută");
        add(searchButton, gbc);

        // Zonă pentru afișarea rezultatelor
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), gbc);

        // Acțiune buton Caută
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            searchRute(searchText);
        });

        // Butonul de Back
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        JButton backButton = new JButton("Back");
        add(backButton, gbc);

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

    private void searchRute(String searchText) {
        String query = "SELECT * FROM ruta WHERE traseu LIKE ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + searchText + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                resultArea.setText(""); // Clear previous results
                while (rs.next()) {
                    String traseu = rs.getString("traseu");
                    double nrKm = rs.getDouble("nr_km");
                    String numeLivrator = rs.getString("nume_livrator");
                    int numarOpriri = rs.getInt("numar_opriri");
                    resultArea.append("Traseu: " + traseu + "\n");
                    resultArea.append("KM: " + nrKm + "\n");
                    resultArea.append("Nume Livrator: " + numeLivrator + "\n");
                    resultArea.append("Numar Opriri: " + numarOpriri + "\n\n");
                }
                if (resultArea.getText().isEmpty()) {
                    resultArea.setText("Nu s-au găsit rute.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Eroare la căutarea rutelor.");
        }
    }

    private Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
