package sourcemaking.behavioral.visitor.example2;

/*
在一个层次内完成多次分发

问题：如果要添加一个新的观察对象，就必须改变Visitor 接口，然后在每一个观察者类中实现这个方法。
解决方案：使用 ReflectiveVisitor，在Visitor接口中只需要一个方法。其他的 visit 方法可以在之后需要时再添加。
 */

import java.lang.reflect.Method;

/*
!!!!!!!!!!!!!!注意：方法的可见性影响到反射机制
 */

// element 层级
interface Element {
    void accept(ReflectiveVisitor visitor);
}

class This implements Element {
    @Override
    public void accept(ReflectiveVisitor visitor) {
        visitor.visit(this);
    }

    public String getThis() {
        return "This";
    }
}

class That implements Element {
    @Override
    public void accept(ReflectiveVisitor visitor) {
        visitor.visit(this);
    }

    public String getThat() {
        return "That";
    }
}

class TheOther implements Element {
    @Override
    public void accept(ReflectiveVisitor visitor) {
        visitor.visit(this);
    }

    public String getTheOther() {
        return "TheOther";
    }
}

// 操作层
abstract class ReflectiveVisitor {
    public abstract void visit(Object o);

    public void visitTheOther(TheOther e) {
        System.out.println("ReflectiveVisitor: do base on " + e.getTheOther());
    }

    // 1 在当前类中找 观察对象 的类名
    // 2 在父类中找 观察对象 的类名
    // 3 在接口中找 观察对象 的类名
    // 4 在当前类中找观察对象
    protected Method getMethod(Class source) {
        Class clazz = source;
        Method method = null;
        while (method == null && clazz != Object.class) {
            String clazzName = clazz.getName();
            clazzName = "visit" + clazzName.substring(clazzName.lastIndexOf('.') + 1);
            try {
                method = getClass().getMethod(clazzName, clazz);
            } catch (NoSuchMethodException e) {
                clazz = clazz.getSuperclass();
            }
        }
        if (clazz == Object.class) {
            // 查找接口
            Class[] interfaces = source.getInterfaces();
            for (Class intf : interfaces) {
                String interfaceName = intf.getName();
                interfaceName = "visit" + interfaceName.substring(interfaceName.lastIndexOf('.') + 1);
                try {
                    method = getClass().getMethod(interfaceName, intf);
                } catch (NoSuchMethodException e) {
                    //
                }
            }
        }
        if (method == null) {
            try {
                method = getClass().getMethod("visitObject", Object.class);
            } catch (Exception e) {
                //
            }
        }
        return method;
    }
}

class UpVisitor extends ReflectiveVisitor {
    @Override
    public void visit(Object o) {
        try {
            getMethod(o.getClass()).invoke(this, o);
        } catch (Exception e) {
            System.out.println("UpVisitor - no appropriate visit() method");
        }
    }

    public void visitThis(This element) {
        System.out.println("UpVisitor: do up on " + element.getThis());
    }

    public void visitObject(Object o) {
        System.out.println("UpVisitor: generic visitObject() method");
    }
}

class DownVisitor extends ReflectiveVisitor {
    public void visit(Object o) {
        try {
            getMethod(o.getClass()).invoke(this, o);
        } catch (Exception e) {
            System.out.println("DownVisitor - no appropriate visit() method");
        }
    }

    public void visitThat(That element) {
        System.out.println("DownVisitor: do Down on " + element.getThat());
    }
}

public class VisitorDemo {
    public static void main(String[] args) {
        Element[] list = {new This(), new That(), new TheOther()};
        UpVisitor up = new UpVisitor();
        DownVisitor down = new DownVisitor();
        for (Element element : list)
            element.accept(up);
        for (Element element : list)
            element.accept(down);
    }
}
