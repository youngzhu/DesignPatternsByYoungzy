package sourcemaking.behavioral.interpreter.example1.before;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
这是Pascal数据结构书中一种设计的改编。
目的是在已知操作优先级的情况下使用栈将常见的中缀表达式转换成后缀表达式
 */
public class InterpreterDemo {
    public static boolean precedence(char a, char b) {
        String high = "*/", low = "+-";
        if (a == '(') {
            return false;
        }
        if (a == ')' && b == '(') {
            System.out.println(")-(");
            return false;
        }
        if (b == '(') {
            return false;
        }
        if (b == ')') {
            return true;
        }
        if (high.indexOf(a) > -1 && low.indexOf(b) > -1) {
            return true;
        }
        if (high.indexOf(a) > -1 && high.indexOf(b) > -1) {
            return true;
        }
        // 没有检验冗余的If语句
        if (low.indexOf(a) > -1 && low.indexOf(b) > -1) {
            return true;
        }
        return false;
    }

    public static String convertToPostfix(String expr) {
        Stack<Character> operationsStack = new Stack<>();
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
                while (! (empty = operationsStack.isEmpty()) && precedence(topSymbol = operationsStack.pop(), token.charAt(0))) {
                    out.append(topSymbol);
                    out.append(' ');
                }
                if (! empty) {
                    operationsStack.push(topSymbol);
                }
                if (empty || token.charAt(0) != ')') {
                    operationsStack.push(token.charAt(0));
                } else {
                    topSymbol = operationsStack.pop();
                }
            }
        }
        while (! operationsStack.isEmpty()) {
            out.append(operationsStack.pop());
            out.append(' ');
        }
        return out.toString();
    }

    public static double processPostfix(String postfix, Map<String, Integer> map) {
        Stack<Double> stack = new Stack<>();
        String operations = "+-*/";
        String[] tokens = postfix.split(" ");
        for (String token : tokens) {
            // 如果是数字或变量
            if (operations.indexOf(token.charAt(0)) == -1) {
                double term;
                try {
                    term = Double.parseDouble(token);
                } catch (NumberFormatException e) {
                    term = map.get(token);
                }
                stack.push(term);
            } else {
                // 如果是操作符
                double b = stack.pop();
                double a = stack.pop();
                if (token.charAt(0) == '+') {
                    a = a + b;
                }
                if (token.charAt(0) == '-') {
                    a = a - b;
                }
                if (token.charAt(0) == '*') {
                    a = a * b;
                }
                if (token.charAt(0) == '/') {
                    a = a / b;
                }
                stack.push(a);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String infix = "C * 9 / 5 + 32";
        String postfix = convertToPostfix(infix);
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + postfix);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= 100; i+= 10) {
            map.put("C", i);
            System.out.println("C is " + i + ", F is " + processPostfix(postfix, map));
        }
    }
}
