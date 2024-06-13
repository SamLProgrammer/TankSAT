package models;

public class MatrixTransform {
    public static Vector2D rotate(Vector2D v, double rotationAngle) {
        double angleCos = Math.cos(Math.toRadians(rotationAngle));
        double angleSin = Math.sin(Math.toRadians(rotationAngle));
        // v.scale(1.0/3.0);
        return new Vector2D((v.getX())*angleCos - (v.getY())*angleSin, (v.getX())*angleSin + (v.getY())*angleCos);
    }
}
