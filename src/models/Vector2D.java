package models;

public class Vector2D {

    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
    }

    public static Vector2D lineNormalVector(Line line) {
        Vector2D lineVector = substractVectors(line.getFinalVector(), line.getBeginVector());
        return new Vector2D(lineVector.getY(), -lineVector.getX());
    }

    public static Vector2D middlePointVector(Line line) {
        return new Vector2D((line.getFinalVector().getX() + line.getBeginVector().getX()) / 2,
                (line.getFinalVector().getY() + line.getBeginVector().getY()) / 2);
    }

    public static double dotProduct(Vector2D v1, Vector2D v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public static Vector2D projection(Vector2D onVector, Vector2D vector) {
        double scaleFactor = dotProduct(onVector, vector) / onVector.magnitudSquared();
        return Vector2D.normalize(onVector).scale(scaleFactor);
    }

    public static Vector2D projectionToX(Vector2D onVector, Vector2D vector) {
        double scaleFactor = dotProduct(onVector, vector) / onVector.magnitudSquared();
        return new Vector2D(1, 0).scale(scaleFactor); // normalized x axis
    }

    public Vector2D addToVector(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    public static Vector2D addVectors(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x + v2.x, v1.y + v2.y);
    }

    public Vector2D substractFromVector(Vector2D v) {
        return addToVector(scaleNew(v, -1));
    }

    public static Vector2D substractVectors(Vector2D v1, Vector2D v2) {
        return addVectors(v1, scaleNew(v2, -1));
    }

    public Vector2D scale(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public static Vector2D scaleNew(Vector2D v, double scalar) {
        return new Vector2D(v.x * scalar, v.y * scalar);
    }

    public double magnitud() {
        return Math.sqrt(x * x + y * y);
    }

    public double magnitudSquared() {
        return x * x + y * y;
    }

    public Vector2D normalized() {
        double magnitud = magnitud();
        this.x /= magnitud;
        this.y /= magnitud;
        return this;
    }

    public static Vector2D normalize(Vector2D v) {
        double magnitud = v.magnitud();
        double x = v.x;
        double y = v.y;
        return new Vector2D(x / magnitud, y / magnitud);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" +
                " x='" + getX() + "'" +
                ", y='" + getY() + "'" +
                "}";
    }

}