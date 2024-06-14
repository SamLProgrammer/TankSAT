package models;

public class Component {

    private Shape[] shapes;
    private int shapesAmount;
    private Vector2D rotationPoint;
    private boolean colliding;
    private Vector2D[] baseVertexes;

    public Component(Shape[] shapes, Vector2D rotationPoint) {
        this.shapes = shapes;
        this.rotationPoint = rotationPoint;
        this.shapesAmount = shapes.length;
        this.baseVertexes = getVertexes();
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public Line[] getLines() {
        int linesAmount = 0;

        for (Shape shape : shapes) {
            linesAmount += shape.getLines().length;
        }

        Line[] lines = new Line[linesAmount];
        int index = 0;
        for (Shape shape : shapes) {
            for (Line line : shape.getLines()) {
                lines[index] = line;
                index++;
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
        int index = 0;
        for (Shape shape : shapes) {
            for (Vector2D vertex : shape.getVertexes()) {
                vertexes[index] = vertex;
                index++;
            }
        }

        return vertexes;
    }

    public Vector2D[] getBaseVertexes() {
        return baseVertexes;
    }

    public int getShapesAmount() {
        return shapesAmount;
    }

    public Vector2D getRotationPoint() {
        return rotationPoint;
    }

    public boolean isColliding() {
        return colliding;
    }

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }
}
