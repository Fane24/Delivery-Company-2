package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeniuPrincipal extends JPanel {
    private JFrame parentFrame;

    public MeniuPrincipal(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Adăugarea logo-ului companiei
        JLabel logoLabel = new JLabel(resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/logo.png"), 150, 75));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 4; // Acum acoperă toate cele patru coloane
        add(logoLabel, gbc);

        // Resetare gridwidth pentru butoane
        gbc.gridwidth = 1;
        gbc.gridy = 1;

        // Adăugarea butonului Client
        gbc.gridx = 0;
        JButton clientButton = new JButton("Client", resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/clienti.png"), 80, 80));
        clientButton.setHorizontalTextPosition(SwingConstants.CENTER);
        clientButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        add(clientButton, gbc);

        // Adăugarea butonului Livrator
        gbc.gridx = 1;
        JButton livratorButton = new JButton("Livrator", resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/livrator.png"), 80, 80));
        livratorButton.setHorizontalTextPosition(SwingConstants.CENTER);
        livratorButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        add(livratorButton, gbc);

        // Adăugarea butonului Rute
        gbc.gridx = 2;
        JButton ruteButton = new JButton("Rute", resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/rute.png"), 80, 80));
        ruteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        ruteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        add(ruteButton, gbc);

        // Adăugarea butonului Comenzi
        gbc.gridx = 3;
        JButton comenziButton = new JButton("Comenzi", resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/comenzi.png"), 80, 80));
        comenziButton.setHorizontalTextPosition(SwingConstants.CENTER);
        comenziButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        add(comenziButton, gbc);

        // Acțiune buton Client
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new ClientPanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });

        // Acțiune buton Livrator
        livratorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new LivratorPanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });

        // Acțiune buton Rute
        ruteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new RutePanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });

        // Acțiune buton Comenzi
        comenziButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new GestiuneComenziPanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
    }

    private Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
