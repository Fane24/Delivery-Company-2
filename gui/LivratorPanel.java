package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LivratorPanel extends JPanel {
    private JFrame parentFrame;

    public LivratorPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Adăugarea butonului Livrator Existenti
        JButton existingLivratorButton = new JButton("Livrator Existenti", resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/livrator existent.png"), 100, 100));
        existingLivratorButton.setHorizontalTextPosition(SwingConstants.CENTER);
        existingLivratorButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        gbc.gridy++;
        add(existingLivratorButton, gbc);

        // Adăugarea butonului Livrator Nou
        JButton newLivratorButton = new JButton("Livrator Nou", resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/livrator nou.png"), 100, 100));
        newLivratorButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newLivratorButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        gbc.gridy++;
        add(newLivratorButton, gbc);

        // Butonul de Back
        gbc.gridy++;
        JButton backButton = new JButton("Back");
        add(backButton, gbc);

        // Acțiune buton Livrator Existenti
        existingLivratorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new CautareLivratoriExistentiPanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });

        // Acțiune buton Livrator Nou
        newLivratorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new AdaugaLivratorPanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
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

    private Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
