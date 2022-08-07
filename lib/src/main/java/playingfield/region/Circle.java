package playingfield.region;

import edu.wpi.first.math.geometry.Translation2d;

public class Circle extends Polygon {

  // Create 360 vertices to represent a circle as a polygon
  private static int N = 360;

  public Circle(Translation2d center, double radius) {
    super(generateVertices(center, radius));
  }

  private static Translation2d[] generateVertices(Translation2d center, double radius) {
    // https://math.stackexchange.com/a/2547424
    Translation2d[] vertices = new Translation2d[N];
    for (int k = 0; k < N; k++) {
      vertices[k] = new Translation2d(
        (radius * Math.cos((2 * k * Math.PI) / N)) + center.getX(),
        (radius * Math.sin((2 * k * Math.PI) / N)) + center.getY()
      );
    }
    return vertices;
  }

}
