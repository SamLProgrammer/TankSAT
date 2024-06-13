package models;

public class Component {

    private Shape[] shapes;
    private int shapesAmount;
    private Vector2D rotationPoint;

    public Component(Shape[] shapes, Vector2D rotationPoint) {
        this.shapes = shapes;
        this.rotationPoint = rotationPoint;
        this.shapesAmount = shapes.length;
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public int getShapesAmount() {
        return shapesAmount;
    }

    public Vector2D getRotationPoint() {
        return rotationPoint;
    }

}
