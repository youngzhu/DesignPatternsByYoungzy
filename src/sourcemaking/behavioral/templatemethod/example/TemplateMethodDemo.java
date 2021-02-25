package sourcemaking.behavioral.templatemethod.example;

/*
1. 将骨架算法在模板方法类中标准化
2. 通用的步骤实现放在基类中
3. 需要个别实现的步骤在基类中是个“占位符”
4. 子类可以覆盖“占位符”方法
5. 子类也可以覆盖已实现的方法
6. 子类可以覆盖和回调基类中的方法
 */

abstract class Generalization {
    // 1. 标准化骨架算法
    void findSolution() {
        stepOne();
        stepTwo();
        stepThr();
        stepFor();
    }

    // 2. 通用的步骤
    private void stepOne() {
        System.out.println("Generalization.step 1");
    }

    // 3. 占位符方法
    abstract void stepTwo();
    abstract void stepThr();

    void stepFor() {
        System.out.println("Generalization.step 4");
    }

}

abstract class Specialization extends Generalization {
    // 4. 子类可以覆盖 占位符方法
    // 重新标准化
    protected void stepThr() {
        step3_1();
        step3_2();
        step3_3();
    }

    private void step3_1() {
        System.out.println("Specialization.step 3_1");
    }

    abstract protected void step3_2();

    private void step3_3() {
        System.out.println("Specialization.step 3_3");
    }
}

class Realization extends Specialization {
    protected void stepTwo() {
        System.out.println("Realization.step 2");
    }

    protected void step3_2() {
        System.out.println("Realization.step 3_2");
    }

    // 6. 可以覆盖和回调父类中的方法
    protected void stepFor() {
        System.out.println("Realization.step 4");
        super.stepFor();
    }
}

public class TemplateMethodDemo {
    public static void main(String[] args) {
        Generalization algorithm = new Realization();
        algorithm.findSolution();
    }
}
