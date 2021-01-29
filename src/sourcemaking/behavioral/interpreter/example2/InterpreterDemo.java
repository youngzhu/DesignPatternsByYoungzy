package sourcemaking.behavioral.interpreter.example2;
/*
这个模式没有地址解析。
当语法非常复杂时，其他技术（如分析器）可能更合适。
 */

class StkChar {
    private char[] arr = new char[9];
    private int sp = -1;

    void push(char ch) {
        if (! isFull())
            arr[++sp] = ch;
    }

    char pop() {
        if (isEmpty())
            return '\0';
        return arr[sp--];
    }

    boolean isFull() {
        return sp == arr.length - 1;
    }

    boolean isEmpty() {
        return sp == -1;
    }
}

class StkInt {
    private int[] arr = new int[9];
    private int sp = -1;

    void push(int ch) {
        if (! isFull())
            arr[++sp] = ch;
    }

    int pop() {
        if (isEmpty())
            return 0;
        return arr[sp--];
    }

    boolean isFull() {
        return sp == arr.length - 1;
    }

    boolean isEmpty() {
        return sp == -1;
    }
}
public class InterpreterDemo {
    static sourcemaking.behavioral.interpreter.example1.before.InterpreterDemo interpreter
            = new sourcemaking.behavioral.interpreter.example1.before.InterpreterDemo();

    public static String convertToPostfix(String in) {
        StkChar operationsStack = new StkChar();
        StringBuilder out = new StringBuilder();
        String operations = "+-*/()";
        char topSymbol = '+';
        boolean empty;

        for (int i = 0; i < in.length(); i++) {
            if (operations.indexOf(in.charAt(i)) == -1) {
                out.append(in.charAt(i));
            } else {
                while (!(empty = operationsStack.isEmpty())
                        && interpreter.precedence(topSymbol = operationsStack.pop(), in.charAt(i))) {
                    out.append(topSymbol);

                }
                if (! empty) {
                    operationsStack.push(topSymbol);
                }
                if (empty || in.charAt(i) != ')')
                    operationsStack.push(in.charAt(i));
                else
                    topSymbol = operationsStack.pop();
            }
        }
        while (! operationsStack.isEmpty())
            out.append(operationsStack.pop());
        return out.toString();
    }

    public static int evaluate(String in) {
        StkInt stkInt = new StkInt();
        String operations = "+-*/";
        for (int a, b, i = 0; i < in.length(); i ++) {
            if (operations.indexOf(in.charAt(i)) == -1) {
                stkInt.push(in.charAt(i) - 48);
            } else {
                b = stkInt.pop();
                a = stkInt.pop();
                if (in.charAt(i) == '+') {
                    a = a + b;
                }
                if (in.charAt(i) == '-')
                    a = a - b;
                if (in.charAt(i) == '*')
                    a = a * b;
                if (in.charAt(i) == '/')
                    a = a / b;
                stkInt.push(a);
            }
        }
        return stkInt.pop();
    }

    public static void main(String[] args) {
        String in = "(3-(4*(5+6))/(7-8))*9/4";
        System.out.print(in);
        String postfix = convertToPostfix(in);
        System.out.print("---" + postfix);
        System.out.println("---" + evaluate(postfix));
    }
}
