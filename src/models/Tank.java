package models;

public class Tank {
    private boolean colliding;
    private Shape[] shapes;
    private Component body;
    private Component gun;
    private Component gunBase;
    private Component leftWeapon;
    private Component rightWeapon;
    private Vector2D direction;
    private MotionEngine motionEngine;
    private ColliderEngine colliderEngine;
    private AimingEngine aimingEngine;

    public Tank(ColliderEngine colliderEngine, Component body, Component gun, Component gunBase, Component leftWeapon,
            Component rightWeapon) {
        initComponents(colliderEngine, body, gun, gunBase, leftWeapon, rightWeapon);
    }

    private void initComponents(ColliderEngine colliderEngine, Component body, Component gun, Component gunBase,
            Component leftWeapon, Component rightWeapon) {
        this.motionEngine = new MotionEngine(this);
        this.colliderEngine = colliderEngine;
        this.body = body;
        this.gun = gun;
        this.gunBase = gunBase;
        this.leftWeapon = leftWeapon;
        this.rightWeapon = rightWeapon;
        Shape[] shapes = new Shape[gun.getShapesAmount() + body.getShapesAmount()];
        this.shapes = new Shape[shapes.length];

        int index = 0;
        for (Shape shape : gun.getShapes()) {
            this.shapes[index] = shape;
            index += 1;
        }

        for (Shape shape : body.getShapes()) {
            this.shapes[index] = shape;
            index += 1;
        }
        this.direction = new Vector2D(0, 1);
    }

    public Line[] getLines() {
        int linesAmount = 0;

        for (Shape shape : shapes) {
            linesAmount += shape.getLines().length;
        }

        Line[] lines = new Line[linesAmount];

        for (Shape shape : shapes) {
            for (Line line : shape.getLines()) {
                linesAmount -= 1;
                lines[linesAmount] = line;
            }
        }

        return lines;
    }

    public Vector2D[] getVertexes() {
        int vertexesAmount = 0;
        for (Shape shape : shapes) {
            vertexesAmount += shape.getVertexes().length;
        }

        Vector2D[] vertexes = new Vector2D[vertexesAmount];

        for (Shape shape : shapes) {
            for (Vector2D vertex : shape.getVertexes()) {
                vertexesAmount -= 1;
                vertexes[vertexesAmount] = vertex;
            }
        }
        return vertexes;
    }

    public void aim(double i) {
        for (Shape shape : gun.getShapes()) {
            for (int j = 0; j < shape.getOriginalVertexes().length; j++) {
                Vector2D rotatedVertex = MatrixTransform.rotate(new Vector2D(0, 0), shape.getOriginalVertexes()[j], i);
                shape.getVertexes()[j].setX(rotatedVertex.getX() + gun.getRotationPoint().getX());
                shape.getVertexes()[j].setY(rotatedVertex.getY() + gun.getRotationPoint().getY());
            }
        }
        if (colliderEngine.getTank() != null) {
            if (colliderEngine.checkTankGunCollisions()) {
                aim(aimingEngine.getLastRotationGrades());
            } else {
                aimingEngine.setLastRotationGrades(i);
            }
        }
    }

    public boolean isColliding() {
        return colliding;
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public Component getBody() {
        return body;
    }

    public Component getGun() {
        return gun;
    }

    public Vector2D getDirection() {
        return direction;
    }

    public void move(double dt) {
        Vector2D movingDirection = new Vector2D(0, 0);
        if (motionEngine.isMovingForward()) {
            movingDirection = Vector2D.addVectors(movingDirection, direction).scale(dt);
        } else if (motionEngine.isMovingBackward()) {
            movingDirection = Vector2D.addVectors(movingDirection, direction).scale(-dt);
        }

        extracted(movingDirection);
        gun.getRotationPoint().addToVector(movingDirection);
        if (colliderEngine.checkTankBodyCollisions() || colliderEngine.checkTankGunCollisions()) {
            extracted(movingDirection.scale(-1.0));
            gun.getRotationPoint().addToVector(movingDirection);
        }
        rotate(dt);
    }

    private void extracted(Vector2D movingDirection) {
        for (Shape shape : body.getShapes()) {
            for (Vector2D vertex : shape.getVertexes()) {
                vertex.addToVector(movingDirection);
            }
        }
        body.getRotationPoint().addToVector(movingDirection);
        for (Shape shape : gun.getShapes()) {
            for (Vector2D vertex : shape.getVertexes()) {
                vertex.addToVector(movingDirection);
            }
        }
    }

    public void rotate(double dt) {
        double originalDt = dt;
        dt *= 100;
        double grades = 0;
        if (motionEngine.isRotatingLeft()) {
            grades += 1;
        } else if (motionEngine.isRotatingRight()) {
            grades -= 1;
        }
        grades *= dt;

        for (Shape shape : body.getShapes()) {
            shape.rotate(grades, body.getRotationPoint());
        }
        Vector2D adjustedDirection = MatrixTransform.rotate(new Vector2D(0, 0), direction, grades);
        direction.setX(adjustedDirection.getX());
        direction.setY(adjustedDirection.getY());

        if (colliderEngine.getTank() != null && colliderEngine.checkTankBodyCollisions()) {
            rotate(-originalDt);
        }
    }

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
        for (Shape shape : shapes) {
            shape.setColliding(colliding);
        }
    }

    public MotionEngine getMotionEngine() {
        return motionEngine;
    }

    public Component getLeftWeapon() {
        return leftWeapon;
    }

    public Component getRightWeapon() {
        return rightWeapon;
    }

    public void setAimingEngine(AimingEngine aimingEngine) {
        this.aimingEngine = aimingEngine;
    }
}
