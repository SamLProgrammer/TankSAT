package models;

public class Tank {
    private boolean colliding;
    private Shape[] shapes;
    private Component body;
    private Component gun;
    private Vector2D direction;
    private MotionEngine motionEngine;

    public Tank(Component body, Component gun) {
        initComponents(body, gun);
    }

    private void initComponents(Component body, Component gun) {
        this.motionEngine = new MotionEngine(this);
        this.body = body;
        this.gun = gun;
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
        aim(-90);
        rotate(-90);
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
            shape.rotate(i, gun.getRotationPoint());
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
        gun.getRotationPoint().addToVector(movingDirection);
        rotate(dt);
    }

    public void rotate(double dt) {
        dt *= 100;
        double grades = 0;
        if (motionEngine.isRotatingLeft()) {
            grades += 1;
        } else if (motionEngine.isRotatingRight()) {
            grades -= 1;
        }
        for (Shape shape : body.getShapes()) {
            shape.rotate(grades*dt, body.getRotationPoint());
        }
        Vector2D adjustedDirection = MatrixTransform.rotate(direction, grades*dt);
        direction.setX(adjustedDirection.getX());
        direction.setY(adjustedDirection.getY());
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
}
