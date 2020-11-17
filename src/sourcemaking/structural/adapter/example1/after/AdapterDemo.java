package sourcemaking.structural.adapter.example1.after;

public class AdapterDemo {
    public static void main(String[] args) {
        Shape[] shapes = {new LineAdapter(new Line()),
                new RectangleAdapter(new Rectangle())};

        int x1 = 10, y1 = 20;
        int x2 = 30, y2 = 60;
//        int width = 40, height = 40;

        for (Shape shape : shapes) {
            shape.draw(x1, y1, x2, y2);
        }
    }
}

class Line {
    public void draw(int x1, int y1, int x2, int y2) {
        System.out.println("Line from point A(" + x1 + ", " + y1
        + "), to point B(" + x2 + ", " + y2 + ")");
    }
}

class Rectangle {
    public void draw(int x, int y, int width, int height) {
        System.out.println("Rectangle with coordinate left-down point ("
        + x + ", " + y + "), width:" + width + ", height:" + height);
    }
}

interface Shape {
    void draw(int a, int b, int c, int d);
}

class LineAdapter implements Shape {
    private Line adaptee;

    public LineAdapter(Line line) {
        this.adaptee = line;
    }

    @Override
    public void draw(int a, int b, int c, int d) {
        adaptee.draw(a, b, c, d);
    }
}

class RectangleAdapter implements Shape {
    private Rectangle adaptee;

    public RectangleAdapter(Rectangle rectangle) {
        this.adaptee = rectangle;
    }

    @Override
    public void draw(int a, int b, int c, int d) {
        int x = Math.min(a, c);
        int y = Math.min(b, d);
        int width = Math.abs(a - c);
        int height = Math.abs(b - d);
        adaptee.draw(x, y, width, height);
    }
}