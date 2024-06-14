package models;

import java.util.ArrayList;

public class Space {
    public static final double HORIZONTAL = 16.0;
    public static final double VERTICAL = 9.0;

    private Tank tank;
    private ArrayList<PenObstacle> penObstacles;
    private ColliderEngine colliderEngine;
    private AimingEngine aimingEngine;

    public Space() {
        initComponents();
    }

    private void initComponents() {
        penObstacles = new ArrayList<>();
        Vector2D[] penObstacleVertex = new Vector2D[8];
        penObstacleVertex[0] = new Vector2D(-2, 1).scale(1 / 7.0);
        penObstacleVertex[1] = new Vector2D(-1, 2).scale(1 / 7.0);
        penObstacleVertex[2] = new Vector2D(1, 2).scale(1 / 7.0);
        penObstacleVertex[3] = new Vector2D(2, 1).scale(1 / 7.0);
        penObstacleVertex[4] = new Vector2D(2, -1).scale(1 / 7.0);
        penObstacleVertex[5] = new Vector2D(1, -2).scale(1 / 7.0);
        penObstacleVertex[6] = new Vector2D(-1, -2).scale(1 / 7.0);
        penObstacleVertex[7] = new Vector2D(-2, -1).scale(1 / 7.0);
        for (Vector2D vector2d : penObstacleVertex) {
            // vector2d.addToVector(new Vector2D(-7, 3.5));
            vector2d.addToVector(new Vector2D(-2, 1));
        }
        penObstacles.add(new PenObstacle((new Vector2D(0, 0)).addToVector(new Vector2D(-7, 3.7)), penObstacleVertex));

        double squareUnit = 0.3 / 7;
        Square tankBody = new Square(-0.15, 0.15, 0.3, 0.4);
        Square tankGunBase = new Square(-0.15 + squareUnit, 0.15 - squareUnit, squareUnit * 5, squareUnit * 4);
        Square leftGun = new Square(-0.15 + 2 * squareUnit, 0.15 + 2 * squareUnit, squareUnit, squareUnit * 4);
        Square rightGun = new Square(-0.15 + 4 * squareUnit, 0.15 + 2 * squareUnit, squareUnit, squareUnit * 4);

        Square leftGunHole = new Square(-0.15 + 2 * squareUnit + squareUnit / 7,
                0.15 + 2 * squareUnit + (4 * squareUnit / 5), +(4 * squareUnit / 5), +(4 * squareUnit / 5));
        Square rightGunHole = new Square(-0.15 + 4 * squareUnit + squareUnit / 7,
                0.15 + 2 * squareUnit + (4 * squareUnit / 5), +(4 * squareUnit / 5), +(4 * squareUnit / 5));
        Square wheel1 = new Square(0.15, 0.15 - squareUnit, squareUnit, squareUnit);
        Square wheel2 = new Square(0.15, 0.15 - squareUnit * 3, squareUnit, squareUnit);
        Square wheel3 = new Square(0.15, 0.15 - squareUnit * 5, squareUnit, squareUnit);
        Square wheel4 = new Square(-0.15 - squareUnit, 0.15 - squareUnit, squareUnit, squareUnit);
        Square wheel5 = new Square(-0.15 - squareUnit, 0.15 - squareUnit * 3, squareUnit, squareUnit);
        Square wheel6 = new Square(-0.15 - squareUnit, 0.15 - squareUnit * 5, squareUnit, squareUnit);
        Square wheel7 = new Square(-0.15 + 2 * squareUnit, 0.15 - 0.4, squareUnit, squareUnit);
        Square wheel8 = new Square(-0.15 + 4 * squareUnit, 0.15 - 0.4, squareUnit, squareUnit);
        Shape[] gunShapes = { tankGunBase, leftGun, rightGun, leftGunHole, rightGunHole };
        Shape[] gunBodyShapes = { tankGunBase };
        Shape[] leftWeaponShapes = { leftGun, leftGunHole };
        Shape[] rightWeaponShapes = { rightGun, rightGunHole };
        Shape[] bodyShapes = { tankBody, wheel1, wheel2, wheel3, wheel4, wheel5, wheel6, wheel7, wheel8, };

        colliderEngine = new ColliderEngine(penObstacles);
        Tank tank = new Tank(colliderEngine,
                new Component(bodyShapes,
                        new Vector2D((tankBody.getX() + tankBody.getWidth()) / 2,
                                (tankBody.getY() + tankBody.getHeight()) / 2)),
                new Component(gunShapes, new Vector2D((tankGunBase.getX() + tankGunBase.getWidth()) / 2,
                        (tankGunBase.getY() + tankGunBase.getHeight()) / 2)),
                new Component(gunBodyShapes, null),
                new Component(leftWeaponShapes, null), new Component(rightWeaponShapes, null));
        colliderEngine.setTank(tank);
        this.aimingEngine = new AimingEngine(tank);
        this.tank = tank;
    }

    public void move(double dt) {
        tank.move(dt);
    }

    public ColliderEngine getColliderEngine() {
        return colliderEngine;
    }

    public Tank getTank() {
        return tank;
    }

    public ArrayList<PenObstacle> getPenObstacles() {
        return penObstacles;
    }

    public AimingEngine getAimingEngine() {
        return aimingEngine;
    }
}
