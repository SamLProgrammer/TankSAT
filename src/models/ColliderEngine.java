package models;

import java.util.ArrayList;

public class ColliderEngine {
    private Tank tank;
    private ArrayList<PenObstacle> penObstacles;

    public ColliderEngine(ArrayList<PenObstacle> penObstacles) {
        this.penObstacles = penObstacles;
    }

    public boolean checkTankBodyCollisions() {
        return checkComponentCollisions(tank.getBody());
    }

    public boolean checkTankGunCollisions() {
        return checkComponentCollisions(tank.getRightWeapon()) || checkComponentCollisions(tank.getLeftWeapon());
    }

    public boolean checkComponentCollisions(Component component) {
        boolean colliding = true;
        for (PenObstacle penObstacle : penObstacles) {

            for (Line line : component.getLines()) {
                Vector2D lineAxisVector = Vector2D.lineNormalVector(line);
                double triangleMinProjection = Double.MAX_VALUE;
                double triangleMaxProjection = Integer.MIN_VALUE;
                double triangle2MinProjection = Double.MAX_VALUE;
                double triangle2MaxProjection = Integer.MIN_VALUE;
                for (Vector2D vertex : component.getVertexes()) {
                    double projection = Vector2D.dotProduct(lineAxisVector, vertex);
                    triangleMinProjection = (projection < triangleMinProjection) ? projection
                            : triangleMinProjection;
                    triangleMaxProjection = (projection > triangleMaxProjection) ? projection
                            : triangleMaxProjection;
                }
                for (Vector2D vertex : penObstacle.getVertexes()) {
                    double projection = Vector2D.dotProduct(lineAxisVector, vertex);
                    triangle2MinProjection = (projection < triangle2MinProjection) ? projection
                            : triangle2MinProjection;
                    triangle2MaxProjection = (projection > triangle2MaxProjection) ? projection
                            : triangle2MaxProjection;
                }
                colliding = colliding && !(triangleMaxProjection <= triangle2MinProjection
                        || triangle2MaxProjection <= triangleMinProjection);
            }

            if (colliding) {
                for (Line line : penObstacle.getLines()) {
                    Vector2D lineAxisVector = Vector2D.lineNormalVector(line);
                    double triangleMinProjection = Double.MAX_VALUE;
                    double triangleMaxProjection = Integer.MIN_VALUE;
                    double triangle2MinProjection = Double.MAX_VALUE;
                    double triangle2MaxProjection = Integer.MIN_VALUE;
                    for (Vector2D vertex : component.getVertexes()) {
                        double projection = Vector2D.dotProduct(lineAxisVector, vertex);
                        triangleMinProjection = (projection < triangleMinProjection)
                                ? projection
                                : triangleMinProjection;
                        triangleMaxProjection = (projection > triangleMaxProjection)
                                ? projection
                                : triangleMaxProjection;
                    }
                    for (Vector2D vertex : penObstacle.getVertexes()) {
                        double projection = Vector2D.dotProduct(lineAxisVector, vertex);
                        triangle2MinProjection = (projection < triangle2MinProjection)
                                ? projection
                                : triangle2MinProjection;
                        triangle2MaxProjection = (projection > triangle2MaxProjection)
                                ? projection
                                : triangle2MaxProjection;
                    }
                    colliding = colliding && !(triangleMaxProjection <= triangle2MinProjection
                            || triangle2MaxProjection <= triangleMinProjection);
                }
            }
        }
        return colliding;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public Tank getTank() {
        return tank;
    }
}
