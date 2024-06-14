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
        Vector2D[] penObstacleVertexesBase = new Vector2D[8];
        penObstacleVertexesBase[0] = new Vector2D(-2, 1).scale(1 / 7.0);
        penObstacleVertexesBase[1] = new Vector2D(-1, 2).scale(1 / 7.0);
        penObstacleVertexesBase[2] = new Vector2D(1, 2).scale(1 / 7.0);
        penObstacleVertexesBase[3] = new Vector2D(2, 1).scale(1 / 7.0);
        penObstacleVertexesBase[4] = new Vector2D(2, -1).scale(1 / 7.0);
        penObstacleVertexesBase[5] = new Vector2D(1, -2).scale(1 / 7.0);
        penObstacleVertexesBase[6] = new Vector2D(-1, -2).scale(1 / 7.0);
        penObstacleVertexesBase[7] = new Vector2D(-2, -1).scale(1 / 7.0);

        double xOffset = 0;
        double yOffset = 0;

        Vector2D initialPoint = new Vector2D(-7, -3.5);

        for(int y = 0; y < 5; y++) {
            xOffset = 0;
            for(int x = 0; x < 5; x++) {
                Vector2D[] penObstacleVertexes = new Vector2D[penObstacleVertexesBase.length];
                Vector2D positionPoint = Vector2D.addVectors(new Vector2D(xOffset, yOffset), initialPoint);
                for (int j = 0; j < penObstacleVertexes.length; j++) {
                    penObstacleVertexes[j] = Vector2D.addVectors(positionPoint, penObstacleVertexesBase[j]);
                }
                penObstacles.add(new PenObstacle(positionPoint, penObstacleVertexes));
                xOffset += HORIZONTAL / 5;
            }
            yOffset += VERTICAL / 5;
        }
        // penObstacles.add(new PenObstacle(new Vector2D(-7, 3.7), penObstacleVertexesBase));

        double squareUnit = 0.3 / 7;
        Square tankBody = new Square(-0.15, 0.15, 0.3, 0.4);
        Square tankGunBase = new Square(-0.15 + squareUnit, 0.15 - squareUnit, squareUnit * 5, squareUnit * 4);
        Square leftGun = new Square(-0.15 + 2 * squareUnit, 0.15 + 2 * squareUnit, squareUnit, squareUnit * 4);
        Square rightGun = new Square(-0.15 + 4 * squareUnit, 0.15 + 2 * squareUnit, squareUnit, squareUnit * 4);

        Square leftGunHole = new Square(-0.15 + 2 * squareUnit + squareUnit / 7,
                0.15 + 2 * squareUnit + (4 * squareUnit / 5), +(4 * squareUnit / 5), +(4 * squareUnit / 5));
        Square rightGunHole = new Square(-0.15 + 4 * squareUnit + squareUnit / 7,
                0.15 + 2 * squareUnit + (4 * squareUnit / 5), +(4 * squareUnit / 5), +(4 * squareUnit / 5));
        // Triangle leftGunHole = new Triangle(new Vector2D(-0.15 + 2 * squareUnit, 0.15 + 2 * squareUnit),
        // new Vector2D(-0.15 + 3 * squareUnit, 0.15 + 2 * squareUnit), new Vector2D(-0.15 + 2.5 * squareUnit, 0.15 + 2 * squareUnit + squareUnit));
        // Triangle rightGunHole = new Triangle(new Vector2D(-0.15 + 4 * squareUnit, 0.15 + 2 * squareUnit),
        // new Vector2D(-0.15 + 5 * squareUnit, 0.15 + 2 * squareUnit), new Vector2D(-0.15 + 4.5 * squareUnit, 0.15 + 2 * squareUnit + squareUnit));
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
