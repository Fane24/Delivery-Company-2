package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RutePanel extends JPanel {
    private JFrame parentFrame;

    public RutePanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Adăugarea butonului Ruta Existenta
        JButton existingRuteButton = new JButton("Ruta Existenta", resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/rute.png"), 100, 100));
        existingRuteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        existingRuteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        gbc.gridy++;
        add(existingRuteButton, gbc);

        // Adăugarea butonului Ruta Noua
        JButton newRuteButton = new JButton("Ruta Noua", resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/ruta noua.png"), 100, 100));
        newRuteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newRuteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        gbc.gridy++;
        add(newRuteButton, gbc);

        // Butonul de Back
        gbc.gridy++;
        JButton backButton = new JButton("Back");
        add(backButton, gbc);

        // Acțiune buton Ruta Existenta
        existingRuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new CautareRuteExistentePanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });

        // Acțiune buton Ruta Noua
        newRuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new AdaugaRutaPanel(parentFrame));
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
