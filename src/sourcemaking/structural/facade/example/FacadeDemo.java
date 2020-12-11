package sourcemaking.structural.facade.example;
/*
1. 确定一组子系统所需要的统一的接口
2. 设计一个封装类可以用来封装对子系统的调用
3. 客户端使用这个门面类（耦合的）
4. 这个门面类/封装类映射了子系统的API
 */

// 1 子系统：笛卡尔坐标
class PointCartesian {
    private double x, y;

    PointCartesian(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

// 1 子系统：极地坐标
class PointPolar {
    private double radius, angle;

    PointPolar(double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
    }

    public void rotate(int angle) {
        this.angle += angle % 360;
    }

    @Override
    public String toString() {
        return "[" + radius + "@" + angle + "]";
    }
}

// 1 需要的接口：move(), rotate()
class Point {
    // 2 封装类
    private PointCartesian pointCartesian;

    public Point(double x, double y) {
        pointCartesian = new PointCartesian(x, y);
    }

    @Override
    public String toString() {
        return pointCartesian.toString();
    }

    // 4 映射接口
    public void move(int x, int y) {
        pointCartesian.move(x, y);
    }

    public void rotate(int angle, Point point) {
        double x = pointCartesian.getX() - point.pointCartesian.getX();
        double y = pointCartesian.getY() - point.pointCartesian.getY();
        PointPolar pointPolar = new PointPolar(Math.sqrt(x * x + y * y),
                Math.atan2(y, x) * 180 / Math.PI);
        // 4 映射接口
        pointPolar.rotate(angle);
        System.out.println("PointPolar is " + pointPolar);
        String str = pointPolar.toString();

        int i = str.indexOf('@');
        double r = Double.parseDouble(str.substring(1, i));
        double a = Double.parseDouble(str.substring(i + 1, str.length() - 1));
        pointCartesian = new PointCartesian(r * Math.cos(a * Math.PI / 180 + point.pointCartesian.getX()),
                r * Math.sin(a * Math.PI / 180 + point.pointCartesian.getY()));
    }
}

class Line {
    private Point o, e;
    public Line(Point ori, Point end) {
        o = ori;
        e = end;
    }

    public void move(int x, int y) {
        o.move(x, y);
        e.move(x, y);
    }

    public void rotate(int angle) {
        e.rotate(angle, o);
    }

    @Override
    public String toString() {
        return "origin is " + o + ", end is " + e;
    }
}

public class FacadeDemo {
    public static void main(String[] args) {
        // 3 使用门面类
        Line lineA = new Line(new Point(2, 4), new Point(5,7));
        System.out.println("before: " + lineA);
        lineA.move(-2, -4);
        System.out.println("after move: " + lineA);
        lineA.rotate(45);
        System.out.println("after rotate: " + lineA);

        Line lineB = new Line(new Point(2, 1), new Point(2.866, 1.5));
        lineB.rotate(30);
        System.out.println("30 degrees to 60 degrees: " + lineB);
    }
}
