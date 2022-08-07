package playingfield.region;

import edu.wpi.first.math.geometry.Translation2d;

public class Polygon implements Region {

  private Translation2d[] vertices;

  public Polygon(Translation2d... vertices) {
    if (vertices.length < 3) {
      throw new IllegalArgumentException("Polygons must have at least 3 vertices");
    }
    // TODO: Maybe some validation to ensure this polygon makes sense
    this.vertices = vertices;
  }

  @Override
  public Translation2d[] getVertices() {
    return this.vertices;
  }

}
