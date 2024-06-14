package controller;

import javax.swing.SwingUtilities;
import models.Space;
import models.TimeStamp;
import views.MainFrame;

public class Controller {
    private MainFrame mainFrame;
    private Space space;

    public Controller() {
        space = new Space();
        mainFrame = new MainFrame(space, space.getAimingEngine());
        initThread();
    }

    private void initThread() {
        TimeStamp timeStamp = new TimeStamp();
        new Thread(() -> {
            while (true) {
                space.move(timeStamp.delta());
                SwingUtilities.invokeLater(() -> {
                    mainFrame.updateFrame(space);
                });
                try {
                    Thread.sleep(17);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
