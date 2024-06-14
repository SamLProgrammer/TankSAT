package models;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import views.MainPanel;

public class AimingEngine extends MouseAdapter {

    private Tank tank;
    private MainPanel focusPanel;
    private double lastRotationGrades;

    public AimingEngine(Tank tank) {
        this.tank = tank;
        tank.setAimingEngine(this);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double xCursor = Transform.xInvertedTransform(e.getX(), focusPanel.getWidth());
        double yCursor = Transform.yInvertedTransform(e.getY(), focusPanel.getHeight());

        double xComponent = xCursor - tank.getGun().getRotationPoint().getX();
        double yComponent = yCursor - tank.getGun().getRotationPoint().getY();

        double cursorGrades = Math.toDegrees(Math.atan2(yComponent, xComponent));
        cursorGrades = (cursorGrades + 360) % 360;

        tank.aim(cursorGrades-90);
    }

    public void setFocusPanel(MainPanel focusPanel) {
        this.focusPanel = focusPanel;
    }

    public double getLastRotationGrades() {
        return lastRotationGrades;
    }

    public void setLastRotationGrades(double lastRotationGrades) {
        this.lastRotationGrades = lastRotationGrades;
    }
}
