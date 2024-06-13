package models;

import java.util.ArrayList;

public class ColliderEngine {
    private ArrayList<Tank> agents;

    public ColliderEngine(ArrayList<Tank> agents) {
        this.agents = agents;
    }

    public void checkCollisions() {
        for (Tank agent : agents) {
            for (Tank agent2 : agents) {
                if (agent2 != agent) {
                    boolean colliding = true;

                    for (Line line : agent.getLines()) {
                        Vector2D lineAxisVector = Vector2D.lineNormalVector(line);
                        double triangleMinProjection = Double.MAX_VALUE;
                        double triangleMaxProjection = Integer.MIN_VALUE;
                        double triangle2MinProjection = Double.MAX_VALUE;
                        double triangle2MaxProjection = Integer.MIN_VALUE;
                        for (Vector2D vertex : agent.getVertexes()) {
                            double projection = Vector2D.dotProduct(lineAxisVector, vertex);
                            triangleMinProjection = (projection < triangleMinProjection) ? projection
                                    : triangleMinProjection;
                            triangleMaxProjection = (projection > triangleMaxProjection) ? projection
                                    : triangleMaxProjection;
                        }
                        for (Vector2D vertex : agent2.getVertexes()) {
                            double projection = Vector2D.dotProduct(lineAxisVector, vertex);
                            triangle2MinProjection = (projection < triangle2MinProjection) ? projection
                                    : triangle2MinProjection;
                            triangle2MaxProjection = (projection > triangle2MaxProjection) ? projection
                                    : triangle2MaxProjection;
                        }
                        colliding = colliding && !(triangleMaxProjection < triangle2MinProjection
                                || triangle2MaxProjection < triangleMinProjection);
                    }

                    if (colliding) {
                    for (Line line : agent2.getLines()) {
                        Vector2D lineAxisVector = Vector2D.lineNormalVector(line);
                        double triangleMinProjection = Double.MAX_VALUE;
                        double triangleMaxProjection = Integer.MIN_VALUE;
                        double triangle2MinProjection = Double.MAX_VALUE;
                        double triangle2MaxProjection = Integer.MIN_VALUE;
                        for (Vector2D vertex : agent.getVertexes()) {
                            double projection = Vector2D.dotProduct(lineAxisVector, vertex);
                            triangleMinProjection = (projection < triangleMinProjection)
                                    ? projection
                                    : triangleMinProjection;
                            triangleMaxProjection = (projection > triangleMaxProjection)
                                    ? projection
                                    : triangleMaxProjection;
                        }
                        for (Vector2D vertex : agent2.getVertexes()) {
                            double projection = Vector2D.dotProduct(lineAxisVector, vertex);
                            triangle2MinProjection = (projection < triangle2MinProjection)
                                    ? projection
                                    : triangle2MinProjection;
                            triangle2MaxProjection = (projection > triangle2MaxProjection)
                                    ? projection
                                    : triangle2MaxProjection;
                        }
                        colliding = colliding && !(triangleMaxProjection < triangle2MinProjection
                                || triangle2MaxProjection < triangleMinProjection);
                    }
                    }
                    // agent.setColliding(colliding); REMEMBER THIS
                    // agent2.setColliding(colliding);
                }
            }
        }
    }
}
