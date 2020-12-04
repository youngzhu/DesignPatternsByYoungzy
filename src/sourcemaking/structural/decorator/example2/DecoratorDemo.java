package sourcemaking.structural.decorator.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DecoratorDemo extends Decorator {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public DecoratorDemo(LCD inner) {
        super(inner);
    }

    @Override
    public void write(String[] s) {
        System.out.print("PASSWORD: ");
        try {
            in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.write(s);
    }

    @Override
    public void read(String[] s) {
        System.out.print("PASSWORD: ");
        try {
            in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.read(s);
    }

    public static void main(String[] args) {
        LCD stream = new DecoratorDemo(new Scramble(new Core()));
        String[] str = {""};
        stream.write(str);
        System.out.println("main: " + str[0]);
        stream.read(str);
    }
}
