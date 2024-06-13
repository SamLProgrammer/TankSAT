package controller;

import javax.swing.SwingUtilities;

import models.AimingEngine;
import models.Space;
import models.TimeStamp;
import views.MainFrame;

public class Controller {
    private MainFrame mainFrame;
    private Space space;

    public Controller() {
        space = new Space();
        AimingEngine me = new AimingEngine(space.getMainAgent());
        mainFrame = new MainFrame(space, me);
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
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
