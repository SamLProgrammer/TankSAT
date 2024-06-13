package models;

public abstract class Shape {
    
    private Vector2D[] vertexes;
    private Line[] lines;
    private boolean colliding;
    private Vector2D centerPoint;

    public Line[] getLines() {
        return lines;
    }

    public Vector2D[] getVertexes() {
        return vertexes;
    }

    protected abstract void rotate(double i, Vector2D rotationPoint2);

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }

    public boolean isColliding() {
        return colliding;
    }

    public void setCenterPoint(Vector2D centerPoint) {
        this.centerPoint = centerPoint;
    }

    public Vector2D getCenterPoint() {
        return centerPoint;
    }
}
