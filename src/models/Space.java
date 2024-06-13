package models;

import java.util.ArrayList;

public class Space {
    public static final double HORIZONTAL = 16.0;
    public static final double VERTICAL = 9.0;

    private ArrayList<Tank> agents;
    private ColliderEngine colliderEngine;
    private Tank mainAgent;

    public Space() {
        initComponents();
    }

    private void initComponents() {
        Triangle t1 = new Triangle(new Vector2D(-1.5, 0.5), new Vector2D(1.2, 1.2), new Vector2D(1, -1.5));
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
        Shape[] bodyShapes = { tankBody, wheel1, wheel2, wheel3, wheel4, wheel5, wheel6, wheel7, wheel8, };

        Tank agent = new Tank(
                new Component(bodyShapes,
                        new Vector2D((tankBody.getX() + tankBody.getWidth()) / 2,
                                (tankBody.getY() + tankBody.getHeight()) / 2)),
                new Component(gunShapes, new Vector2D((tankGunBase.getX() + tankGunBase.getWidth()) / 2,
                        (tankGunBase.getY() + tankGunBase.getHeight()) / 2)));
        mainAgent = agent;
        this.agents = new ArrayList<>();
        agents.add(agent);
        colliderEngine = new ColliderEngine(agents);
    }

    public void move(double dt) {
        mainAgent.move(dt);
        colliderEngine.checkCollisions();
    }

    public ColliderEngine getColliderEngine() {
        return colliderEngine;
    }

    public ArrayList<Tank> getAgents() {
        return agents;
    }

    public Tank getMainAgent() {
        return mainAgent;
    }
}
