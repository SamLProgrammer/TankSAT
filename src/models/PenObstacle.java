package models;

public class PenObstacle extends Shape {

    private Vector2D center;
    private Vector2D[] vertexes;
    private Line[] lines;

    public PenObstacle(Vector2D center, Vector2D[] vertexes) {
        this.center = center;
        initComponents(vertexes);
    }

    private void initComponents(Vector2D[] vertexes) {
        this.vertexes = vertexes;
        this.lines = new Line[vertexes.length];

        for (int i = 0; i < vertexes.length; i++) {
            this.lines[i] = new Line(this.vertexes[i], this.vertexes[(i < vertexes.length-1) ? i+1 : 0]);
        }
    }

    @Override
    protected void rotate(double i, Vector2D rotationPoint2) {
        
    }

    public Line[] getLines() {
        return lines;
    }

    public Vector2D[] getVertexes() {
        return vertexes;
    }
    
}
