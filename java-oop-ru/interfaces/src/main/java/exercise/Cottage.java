package exercise;

// BEGIN
public class Cottage implements Home {

    private double area;
    private int flourCount;

    public Cottage(double area, int flourCount) {
        this.area = area;
        this.flourCount = flourCount;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    @Override
    public int compareTo(Home another) {

        int result = 0;
        if (this.area > another.getArea()) {
            result = 1;
        } else if (this.area < another.getArea()) {
            result = -1;
        }
        return result;
    }

    @Override
    public String toString() {
        return this.flourCount + " этажный коттедж площадью " + this.area + " метров";
    }
}
// END
