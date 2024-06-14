package models;

public class Square extends Shape {

    private Vector2D[] vertexes;
    private Line[] lines;
    private double x;
    private double y;
    private double width;
    private double height;

    public Square(double x, double y, double width, double height) {
        Vector2D vertex1 = new Vector2D(x, y);
        Vector2D vertex2 = new Vector2D(x + width, y);
        Vector2D vertex3 = new Vector2D(x + width, y - height);
        Vector2D vertex4 = new Vector2D(x, y - height);
        setCenterPoint(new Vector2D(x + width / 2, y + height / 2));
        initComponents(vertex1, vertex2, vertex3, vertex4);

    }

    private void initComponents(Vector2D vertex1, Vector2D vertex2, Vector2D vertex3, Vector2D vertex4) {
        this.vertexes = new Vector2D[4];
        this.vertexes[0] = vertex1;
        this.vertexes[1] = vertex2;
        this.vertexes[2] = vertex3;
        this.vertexes[3] = vertex4;
        Vector2D[] originalVertexes = new Vector2D[this.vertexes.length];

        int i = 0;
        for (Vector2D vector2d : this.vertexes) {
            originalVertexes[i] = new Vector2D(vector2d.getX(), vector2d.getY());
            i += 1;
        }

        super.setOriginalVertexes(originalVertexes);

        this.lines = new Line[4];
        this.lines[0] = new Line(vertex1, vertex2);
        this.lines[1] = new Line(vertex1, vertex4);
        this.lines[2] = new Line(vertex3, vertex2);
        this.lines[3] = new Line(vertex3, vertex4);
    }

    @Override
    public Vector2D[] getVertexes() {
        return vertexes;
    }

    @Override
    public Line[] getLines() {
        return lines;
    }

    @Override
    protected void rotate(double i, Vector2D rotationPoint2) {
        Vector2D relativeTo = new Vector2D(rotationPoint2.getX(), rotationPoint2.getY());
        for (Vector2D vertex : vertexes) {
            // vertex.substractFromVector(relativeTo);
            Vector2D rotatedVector = MatrixTransform.rotate(relativeTo, vertex, i);
            vertex.setX(rotatedVector.getX());
            vertex.setY(rotatedVector.getY());
            // vertex.addToVector(relativeTo);
        }
    }

    @Override
    public void setColliding(boolean colliding) {
        super.setColliding(colliding);
    }

    @Override
    public boolean isColliding() {
        return super.isColliding();
    }

    @Override
    public Vector2D getCenterPoint() {
        return super.getCenterPoint();
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
