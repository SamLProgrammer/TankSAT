package models;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MotionEngine extends KeyAdapter {

    private Tank tank;
    private boolean movingForward;
    private boolean movingBackward;
    private boolean rotatingLeft;
    private boolean rotatingRight;

    public MotionEngine(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.movingForward = false;
                break;
            case KeyEvent.VK_DOWN:
                this.movingBackward = false;
                break;
            case KeyEvent.VK_LEFT:
                this.rotatingLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                this.rotatingRight = false;
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (!isMovingForward()) {
                    this.movingBackward = false;
                    this.movingForward = true;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!isMovingForward()) {
                    this.movingForward = false;
                    this.movingBackward = true;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (!isRotatingLeft()) {
                    this.rotatingRight = false;
                    this.rotatingLeft = true;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!isRotatingRight()) {
                    this.rotatingLeft = false;
                    this.rotatingRight = true;
                }
                break;
        }
    }

    public boolean isMovingBackward() {
        return movingBackward;
    }

    public boolean isMovingForward() {
        return movingForward;
    }

    public boolean isRotatingLeft() {
        return rotatingLeft;
    }

    public boolean isRotatingRight() {
        return rotatingRight;
    }

}
