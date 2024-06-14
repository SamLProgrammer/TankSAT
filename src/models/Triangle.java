package models;

public class Triangle extends Shape {

    private Vector2D[] vertexes;
    private Line[] lines;

    public Triangle(Vector2D vertex1, Vector2D vertex2, Vector2D vertex3) {
        initComponents(vertex1, vertex2, vertex3);
    }

    private void initComponents(Vector2D vertex1, Vector2D vertex2, Vector2D vertex3) {
        this.vertexes = new Vector2D[3];
        this.vertexes[0] = vertex1;
        this.vertexes[1] = vertex2;
        this.vertexes[2] = vertex3;

        Vector2D[] originalVertexes = new Vector2D[this.vertexes.length];

        int i = 0;
        for (Vector2D vector2d : vertexes) {
            originalVertexes[i] = new Vector2D(vector2d.getX(), vector2d.getY());
            i += 1;
        }

        super.setOriginalVertexes(originalVertexes);

        this.lines = new Line[3];
        this.lines[0] = new Line(vertex1, vertex2);
        this.lines[1] = new Line(vertex1, vertex3);
        this.lines[2] = new Line(vertex2, vertex3);

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
    public void rotate(double i, Vector2D relativeToV) {
        for (Vector2D vertex : vertexes) {
            Vector2D newVertex = MatrixTransform.rotate(relativeToV, vertex, 1);
            vertex.setX(newVertex.getX());
            vertex.setY(newVertex.getY());
        }
    }

    public Vector2D getRotationPoint() {
        return vertexes[1];
    }

    @Override
    public void setColliding(boolean colliding) {
        super.setColliding(colliding);
    }

    @Override
    public boolean isColliding() {
        return super.isColliding();
    }

}
