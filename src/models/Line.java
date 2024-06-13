package models;

public class Line {
    private Vector2D beginVector;
    private Vector2D finalVector;

    public Line(Vector2D beginVector, Vector2D finalVector) {
        this.beginVector = beginVector;
        this.finalVector = finalVector;
    }

    public Vector2D getBeginVector() {
        return beginVector;
    }

    public Vector2D getFinalVector() {
        return finalVector;
    }

    public void setBeginVector(Vector2D beginVector) {
        this.beginVector = beginVector;
    }

    public void setFinalVector(Vector2D finalVector) {
        this.finalVector = finalVector;
    }

    @Override
    public String toString() {
        return "{" +
            " beginVector='" + getBeginVector() + "'" +
            ", finalVector='" + getFinalVector() + "'" +
            "}";
    }



}
