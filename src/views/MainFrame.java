package views;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import models.AimingEngine;
import models.Space;

public class MainFrame extends JFrame {

    private MainPanel mainPanel;

    public MainFrame(Space space, AimingEngine me) {
        initComponents(space, me);
        initProperties();
    }

    private void initProperties() {
        double screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        double screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        double width = screenWidth / 2.0;
        double height = 4 * screenHeight / 5.0;
        setSize((int) width, (int) height);
        setLocation((int) (screenWidth - width) / 2, 10);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void initComponents(Space space, AimingEngine me) {
        mainPanel = new MainPanel(space, me);
        add(mainPanel);
    }

    public void updateFrame(Space space) {
        mainPanel.updateFrame(space);
    }
}
