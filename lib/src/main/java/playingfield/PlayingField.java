package playingfield;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import playingfield.region.Region;

public class PlayingField {

  /**
   * FRC playing field is 27ftx54ft
   */
  public static double fieldWidthFeet = 27.0;
  public static double fieldLengthFeet = 54.0;

  /* Regions */

  public static boolean inRegion(Translation2d translation, Region region) {
    Translation2d[] vertices = region.getVertices();

    int nvert = vertices.length;
    double testx = translation.getX();
    double testy = translation.getY();

    // https://wrfranklin.org/Research/Short_Notes/pnpoly.html
    int i, j = 0;
    boolean c = false;
    for (i = 0, j = nvert - 1; i < nvert; j = i++) {
      if (((vertices[i].getY() > testy) != (vertices[j].getY() > testy)) && (testx < (vertices[j].getX() - vertices[i].getX()) * (testy - vertices[i].getY()) / (vertices[j].getY() - vertices[i].getY()) + vertices[i].getX())) {
        c = !c;
      }
    }
    return c;
  }

  /* Field Perimeter */

  public static boolean isFacingFieldPerimeter(Pose2d pose) {
    /**
     * Imagine the left/right sides of the field are two horizontal lines. A line can
     * be drawn starting at the robot drawn towards the edge of the field. If the two
     * lines (the robot + either the right or left side line) intersect within the
     * field geometry, the robot is considered facing the edge of the field.
     */
    double angleRadians = pose.getRotation().getRadians();
    // pi/2 and 3pi/2 are undefined slopes
    // Additionally, if the robot is facing at a 90/270, then the robot is
    // confirmed facing an edge of the field.
    if (angleRadians == Math.PI/2 || angleRadians == (3 * Math.PI/2)) {
      return true;
    }
    double m = Math.tan(pose.getRotation().getRadians());
    // If the line is flat, the robot is not facing the field perimeter
    if (m == 0) {
      return false;
    }
    double b = pose.getY() - (m * pose.getX());
    double y = 0;
    if (0 < angleRadians && angleRadians < Math.PI) {
      // Facing left side - find where y = 27
      y = fieldWidthFeet;
    }
    double x = y - b / m;
    // See if where the line crosses is within the field's geometry
    return x >= 0 && x <= fieldLengthFeet;
  }

}
