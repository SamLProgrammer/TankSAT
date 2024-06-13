package models;

public class Transform {

    public static double xTransform(double x, double containerWidth) {
        return (x * (containerWidth / Space.HORIZONTAL)) + containerWidth / 2;
    }

    public static double yTransform(double y, double containerHeight) {
        y *= -1;
        return ((y * (containerHeight / Space.VERTICAL)) + containerHeight / 2);
    }

    public static double scaleX(double x, double containerWidth) {
        return x * (containerWidth / Space.HORIZONTAL);
    }

    public static double scaleY(double y, double containerHeight) {
        return y * (containerHeight / Space.VERTICAL);
    }

    public static double xInvertedTransform(double x, double containerWidth) {
        return (x - containerWidth / 2) * (Space.HORIZONTAL / containerWidth);
    }

    public static double yInvertedTransform(double y, double containerHeight) {
        return (containerHeight / 2 - y) * (Space.VERTICAL / containerHeight);
    }

}
