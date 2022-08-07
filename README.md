# PlayingField

PlayingField is a library for [FIRST Robotics Competition robots](https://www.firstinspires.org/robotics/frc) to abstract away field-specific conditionals in to an easy to use set of primitives.

PlayingField allows teams to define complex field regions and quickly determine if a robot is within the defined region. Other helper functions include knowing if a robot is facing the field perimeter.

## Using PlayingField

### In Region

Teams can define a `Region` via a `Polygon` with at least 3 vertices or a `Circle` with a center + radius and test wether a point is within that region with the `PlayingField.inRegion` method.

```java
public boolean isInScoringArea() {
  return PlayingField.inRegion(robot.getTranslation(), scoringArea);
}
```

An example of a circular `Region`

```java
// "Scoring area" is a circle in the center of the field with a 2ft radius
private static Region scoringArea = new Circle(
  new Translation2d(54.0 / 2, 27.0 / 2),
  2.0
);
```
An example of a square `Region`

```java
// "Scoring area" is a 2ft square in the center of the field
private static double fieldHalfLengthFeet = PlayingField.fieldLengthFeet / 2;
private static double fieldHalfHeightFeet = PlayingField.fieldHeightFeet / 2;
private static Region scoringArea = new Polygon(
  new Translation2d(fieldHalfLengthFeet - 1, fieldHalfHeightFeet - 1),
  new Translation2d(fieldHalfLengthFeet - 1, fieldHalfHeightFeet + 1),
  new Translation2d(fieldHalfLengthFeet + 1, fieldHalfHeightFeet - 1),
  new Translation2d(fieldHalfLengthFeet + 1, fieldHalfHeightFeet + 1),
);
```

To maximize performance, `Region`s should be constructed once during boot and reused as opposed to being constructed continuously on a run loop.

Note that `PlayingField.inRegion` excludes points that are on the boundaries of a polygon.

### Facing Field Perimeter

Teams can test wether a robot is facing the field perimeter by using the `PlayingField.isFacingFieldPerimeter` and passing the robot's pose.

```java
public boolean isFacingFieldPerimeter() {
  return PlayingField.isFacingFieldPerimeter(robot.getPose());
}
```

## External Dependencies

`PlayingField` depends on [WPILib's](https://github.com/wpilibsuite) [`math.geometry` primitives](https://github.com/wpilibsuite/allwpilib/tree/main/wpimath/src/main/java/edu/wpi/first/math/geometry) to simplify the API to interface with team's existing code.
