public class Vector {
    public double x;
    public double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector scale(double num) {
        return new Vector(x*num, y*num);
    }
    public Vector add(Vector v) {
        return new Vector(x+v.x, y+v.y);
    }
    public Vector subtract(Vector v) {
        return new Vector(x-v.x, y-v.y);
    }
    public double length() {
        return Math.sqrt(x*x + y*y);
    }
}