package sourcemaking.structural.adapter.example2;

/*
1. 确定想要的接口
2. 设计一个包装类使“旧的”可以匹配“新的”
3. 这个包装类（即适配器）包含一个旧类的引用
4. 这个包装类（即适配器）“改造”旧的对象
5. 客户端使用这个新的接口
 */
/* The OLD */

/**
 * 方钉子
 */
class SquarePeg {
    private double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}

/* The NEW */

/**
 * 圆孔
 */
class RoundHole {
    private final int radius;

    public RoundHole(int radius) {
        this.radius = radius;
        System.out.println("RoundHole: max SquarePeg is " + radius * Math.sqrt(2));
    }

    public int getRadius() {
        return radius;
    }
}

//2. 设计一个包装类使“旧的”可以匹配“新的”
class SquarePegAdapter {
    //3. 这个包装类（即适配器）包含一个旧类的引用
    private final SquarePeg squarePeg;

    public SquarePegAdapter(double w) {
        squarePeg = new SquarePeg(w);
    }

    // 1. 确定想要的接口
    public void makeFit(RoundHole roundHole) {
        //4. 这个包装类（即适配器）“改造”旧的对象
        double amount = squarePeg.getWidth() - roundHole.getRadius() * Math.sqrt(2);
        System.out.println("reducing SquarePeg " + squarePeg.getWidth()
                + " by " + (amount < 0 ? 0 : amount) + " amount");
        if (amount > 0) {
            squarePeg.setWidth(squarePeg.getWidth() - amount);
            System.out.println(" width is now " + squarePeg.getWidth());
        }
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        RoundHole roundHole = new RoundHole(5);
        SquarePegAdapter adapter;
        for (int i = 6; i < 10; i++) {
            adapter = new SquarePegAdapter(i);
            // 5. 客户端使用这个新的接口
            adapter.makeFit(roundHole);
        }
    }
}
