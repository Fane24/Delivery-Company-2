package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.GenerateReport;

public class CautareLivratoriExistentiPanel extends JPanel {
    private JTextField searchField;
    private JTextArea resultArea;
    private JFrame parentFrame;

    public CautareLivratoriExistentiPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Adăugarea iconiței
        JLabel iconLabel = new JLabel(resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/livratori.png"), 150, 150));
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

        // Buton pentru generarea raportului PDF pentru livratori
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton generatePdfButton = new JButton("Generează Raport PDF Livratori");
        add(generatePdfButton, gbc);

        // Acțiune buton Caută
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                searchLivrator(searchText);
            }
        });

        // Acțiune buton Generează Raport PDF pentru livratori
        generatePdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerateReport.generateLivratoriPdfReport();
                JOptionPane.showMessageDialog(parentFrame, "Raportul PDF pentru livratori a fost generat cu succes!");
            }
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
                parentFrame.setContentPane(new MeniuPrincipal(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
    }

    private void searchLivrator(String searchText) {
        // Implementare căutare livrator
        // Completează codul pentru a căuta livratori în baza de date și a afișa rezultatele în resultArea
    }

    private Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
