package View;

import Model.Data;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    JPanel activePanel = new MenuView();

    public MainFrame() {
        this.setSize(500,300);
        this.setLocation(500,300);
        this.setBounds(500, 300, 700, 450);
        this.setTitle("ParkingApp");
        this.add(activePanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Data.saveVehicles();
                System.exit(0);
            }
        });
    }

    public void changeActivePanel(JPanel newActivePanel) {
        this.remove(activePanel);
        activePanel = newActivePanel;
        this.add(activePanel);
        this.repaint();
    }
}
