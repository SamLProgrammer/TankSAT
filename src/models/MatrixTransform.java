package models;

public class MatrixTransform {
    public static Vector2D rotate(Vector2D relativeTo, Vector2D v, double rotationAngle) {
        double angleCos = Math.cos(Math.toRadians(rotationAngle));
        double angleSin = Math.sin(Math.toRadians(rotationAngle));
        v.substractFromVector(relativeTo);
        Vector2D rotated = new Vector2D((v.getX())*angleCos - (v.getY())*angleSin, (v.getX())*angleSin + (v.getY())*angleCos);
        rotated.addToVector(relativeTo);
        return rotated;
    }
}
