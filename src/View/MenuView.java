package View;

import Controller.MenuController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuView extends JPanel {
    MenuController controller = new MenuController();

    public MenuView() {
        setLayout(new BorderLayout());
        JPanel buttonsPanel = buttonsPanel();
        buttonsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setBackground(new Color(219, 219, 219));
        this.add(buttonsPanel, BorderLayout.CENTER);
    }

    public JPanel buttonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2, 20, 20));

        JButton entranceButton = menuButton("Arrival", "./resources/icons/entrance.png");
        JButton exitButton = menuButton("Departure", "./resources/icons/exit.png");
        JButton registerButton = menuButton("Add vehicle", "./resources/icons/add.png");
        JButton removeButton = menuButton("Remove vehicle", "./resources/icons/remove.png");
        JButton reportButton = menuButton("Generate report", "./resources/icons/report1.png");
        JButton newMonthButton = menuButton("Start month", "./resources/icons/calendar1.png");

        buttonsPanel.add(entranceButton);
        buttonsPanel.add(exitButton);
        buttonsPanel.add(registerButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(reportButton);
        buttonsPanel.add(newMonthButton);

        entranceButton.addActionListener((e) -> controller.arrival());
        exitButton.addActionListener((e) -> controller.departure());
        registerButton.addActionListener((e) -> controller.add());
        removeButton.addActionListener((e) -> controller.delete());
        reportButton.addActionListener((e) -> controller.printReport());
        newMonthButton.addActionListener((e) -> controller.newMonth());

        return buttonsPanel;
    }

    public JButton menuButton(String text, String imgPath) {
        JButton button = new JButton(text);
        button.setBackground(new Color(22, 23, 21));
        button.setForeground(new Color(255, 255, 255));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusable(true);

        ImageIcon icon = new ImageIcon(imgPath);
        Image img = icon.getImage();
        img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(img);

        button.setIcon(newIcon);
        button.setIconTextGap(20);
        button.setPreferredSize(new Dimension(200, 100));
        return button;
    }
}
