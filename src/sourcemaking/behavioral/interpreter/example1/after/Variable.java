package sourcemaking.behavioral.interpreter.example1.after;

import java.util.Map;

public class Variable implements Operand {
    private String name;

    public Variable(String name) {
        this.name = name;
    }
    @Override
    public double evaluate(Map<String, Integer> context) {
        return context.get(name);
    }

    @Override
    public void traverse(int level) {
        System.out.print(name + " ");

    }
}
