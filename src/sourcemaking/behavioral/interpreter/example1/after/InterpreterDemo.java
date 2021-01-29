package sourcemaking.behavioral.interpreter.example1.after;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
这是遵循了解释器模式意图的一次重构。
所有类都实现了 Operand 接口，包括：求值 evaluate，处理一些变量，递归遍历返回它们的结果。
在这个领域使用解释器模式可能是不适合的。
 */
public class InterpreterDemo {
    public static sourcemaking.behavioral.interpreter.example1.before.InterpreterDemo interpreter
            = new sourcemaking.behavioral.interpreter.example1.before.InterpreterDemo();

    public static String convertToPostfix(String expr) {
        Stack<Character> operationsStack = new Stack();
        StringBuilder out = new StringBuilder();
        String operations = "+-*/()";
        char topSymbol = '+';
        boolean empty;
        String[] tokens = expr.split(" ");
        for (String token : tokens) {
            if (operations.indexOf(token.charAt(0)) == -1) {
                out.append(token);
                out.append(' ');
            } else {
                while (!(empty = operationsStack.isEmpty())
                        && interpreter.precedence(topSymbol = operationsStack.pop(), token.charAt(0))) {
                    out.append(topSymbol);
                    out.append(' ');
                }
                if (!empty) {
                    operationsStack.push(topSymbol);
                }
                if (empty || token.charAt(0) != ')')
                    operationsStack.push(token.charAt(0));
                else
                    topSymbol = operationsStack.pop();
            }
        }
        while (!operationsStack.isEmpty()) {
            out.append(operationsStack.pop());
            out.append(' ');
        }
        return out.toString();
    }

    public static Operand buildSyntaxTree(String tree) {
        Stack<Operand> stack = new Stack<>();
        String operations = "+-*/";
        String[] tokens = tree.split(" ");

        for (String token : tokens) {
            if (operations.indexOf(token.charAt(0)) == -1) {
                // 数字或变量
                Operand term;
                try {
                    term = new Number(Double.parseDouble(token));
                } catch (NumberFormatException e) {
                    term = new Variable(token);
                }
                stack.push(term);
            } else {
                // 操作符
                Expression expr = new Expression(token.charAt(0));
                expr.right = stack.pop();
                expr.left = stack.pop();
                stack.push(expr);
            }

        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String infix = "celsius * 9 / 5 + thirty";
        System.out.println(infix);
        String postfix = convertToPostfix(infix);
        System.out.println(postfix);
        Operand expr = buildSyntaxTree(postfix);
        expr.traverse(1);
        System.out.println();

        Map<String, Integer> map = new HashMap<>();
        map.put("thirty", 30);
        for (int i = 0; i <= 100; i+=10) {
            map.put("celsius", i);
            System.out.println("C is " + i + ", F is " + expr.evaluate(map));
        }
    }
}
