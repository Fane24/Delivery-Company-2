package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientPanel extends JPanel {
    private JFrame parentFrame;

    public ClientPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Adăugarea butonului Client Existenti
        JButton existingClientsButton = new JButton("Clienti Existenti", resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/clienti.png"), 100, 100));
        existingClientsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        existingClientsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        gbc.gridy++;
        add(existingClientsButton, gbc);

        // Adăugarea butonului Client Nou
        JButton newClientButton = new JButton("Client Nou", resizeIcon(new ImageIcon("E:/licenta/Firma_de_Curierat_Delivery_RO/src/resources/images/client nou.png"), 100, 100));
        newClientButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newClientButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        gbc.gridy++;
        add(newClientButton, gbc);

        // Butonul de Back
        gbc.gridy++;
        JButton backButton = new JButton("Back");
        add(backButton, gbc);

        // Acțiune buton Client Existenti
        existingClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new CautareClientExistentPanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });

        // Acțiune buton Client Nou
        newClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new AdaugaClientPanel(parentFrame));
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
