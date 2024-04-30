package exercise;

// BEGIN
class Circle {

    private final Point point;
    private final int radius;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {

        if (radius < 0) {
            throw new NegativeRadiusException("Не удалось посчитать площадь");
        } else {
            return Math.PI * radius * radius;
        }
    }
}
// END
