package views;

import javax.swing.JPanel;
import models.Line;
import models.PenObstacle;
import models.AimingEngine;
import models.Shape;
import models.Space;
import models.Square;
import models.Transform;
import models.Triangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class MainPanel extends JPanel {

    private Space space;

    public MainPanel(Space space, AimingEngine me) {
        initProperties();
        initComponents(space, me);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (space != null) {
            paintAxis(g2);
            paintTank(g2);
            paintObstacles(g2);
        }
    }

    private void paintObstacles(Graphics2D g2) {
        g2.setColor(Color.yellow);
        for (PenObstacle penObstacle : space.getPenObstacles()) {
            for(Line line: penObstacle.getLines()) {
                double x1 = Transform.xTransform(line.getBeginVector().getX(), getWidth());
                double y1 = Transform.yTransform(line.getBeginVector().getY(), getHeight());
                double x2 = Transform.xTransform(line.getFinalVector().getX(), getWidth());
                double y2 = Transform.yTransform(line.getFinalVector().getY(), getHeight());

                g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
            }
        }

    }

    private void paintTank(Graphics2D g2) {
        g2.setColor(space.getTank().isColliding() ? Color.red: Color.white);
        for (Shape shape : space.getTank().getShapes()) {
            if (shape instanceof Triangle) {
                paintTriangle(g2, (Triangle) shape);
            } else if (shape instanceof Square) {
                paintSquare(g2, (Square) shape);
            }
        }
    }

    private void paintSquare(Graphics2D g2, Square shape) {
        for (Line line : shape.getLines()) {
            double x1 = Transform.xTransform(line.getBeginVector().getX(), getWidth());
            double y1 = Transform.yTransform(line.getBeginVector().getY(), getHeight());
            double x2 = Transform.xTransform(line.getFinalVector().getX(), getWidth());
            double y2 = Transform.yTransform(line.getFinalVector().getY(), getHeight());
            g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }
    }

    private void paintAxis(Graphics2D g2) {
        double xAxisX1 = Transform.xTransform(-7, getWidth());
        double xAxisY1 = Transform.yTransform(0, getHeight());
        double xAxisX2 = Transform.xTransform(7, getWidth());
        double xAxisY2 = Transform.yTransform(0, getHeight());

        double yAxisX1 = Transform.xTransform(0, getWidth());
        double yAxisY1 = Transform.yTransform(-4, getHeight());
        double yAxisX2 = Transform.xTransform(0, getWidth());
        double yAxisY2 = Transform.yTransform(4, getHeight());

        g2.setColor(Color.blue);
        g2.drawLine((int) xAxisX1, (int) xAxisY1, (int) xAxisX2, (int) xAxisY2);
        g2.drawLine((int) yAxisX1, (int) yAxisY1, (int) yAxisX2, (int) yAxisY2);
    }

    private void paintTriangle(Graphics2D g2, Shape shape) {
        double l1x1 = Transform.xTransform(shape.getLines()[0].getBeginVector().getX(), getWidth());
        double l1y1 = Transform.yTransform(shape.getLines()[0].getBeginVector().getY(), getHeight());
        double l1x2 = Transform.xTransform(shape.getLines()[0].getFinalVector().getX(), getWidth());
        double l1y2 = Transform.yTransform(shape.getLines()[0].getFinalVector().getY(), getHeight());

        double l2x1 = Transform.xTransform(shape.getLines()[1].getBeginVector().getX(), getWidth());
        double l2y1 = Transform.yTransform(shape.getLines()[1].getBeginVector().getY(), getHeight());
        double l2x2 = Transform.xTransform(shape.getLines()[1].getFinalVector().getX(), getWidth());
        double l2y2 = Transform.yTransform(shape.getLines()[1].getFinalVector().getY(), getHeight());

        double l3x1 = Transform.xTransform(shape.getLines()[2].getBeginVector().getX(), getWidth());
        double l3y1 = Transform.yTransform(shape.getLines()[2].getBeginVector().getY(), getHeight());
        double l3x2 = Transform.xTransform(shape.getLines()[2].getFinalVector().getX(), getWidth());
        double l3y2 = Transform.yTransform(shape.getLines()[2].getFinalVector().getY(), getHeight());

        g2.drawLine((int) l1x1, (int) l1y1, (int) l1x2, (int) l1y2);
        g2.drawLine((int) l2x1, (int) l2y1, (int) l2x2, (int) l2y2);
        g2.drawLine((int) l3x1, (int) l3y1, (int) l3x2, (int) l3y2);
    }

    public void initProperties() {
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    public void initComponents(Space space, AimingEngine me) {
        this.space = space;
        me.setFocusPanel(this);
        addMouseMotionListener(me);
        addKeyListener(space.getTank().getMotionEngine());
        setFocusable(true);
    }

    public void updateFrame(Space space) {
        this.space = space;
        repaint();
    }

}
