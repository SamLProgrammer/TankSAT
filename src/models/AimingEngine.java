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
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double xCursor = Transform.xInvertedTransform(e.getX(), focusPanel.getWidth());
        double yCursor = Transform.yInvertedTransform(e.getY(), focusPanel.getHeight());

        double xComponent = xCursor - tank.getGun().getRotationPoint().getX();
        double yComponent = yCursor - tank.getGun().getRotationPoint().getY();

        double cursorGrades = Math.toDegrees(Math.atan2(yComponent, xComponent));
        cursorGrades = (cursorGrades + 360) % 360;

        double rotationGrades = cursorGrades - lastRotationGrades;
        lastRotationGrades += rotationGrades;

        tank.aim(rotationGrades);
    }

    public void setFocusPanel(MainPanel focusPanel) {
        this.focusPanel = focusPanel;
    }
}
