package sourcemaking.structural.decorator.example4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DecoratorStream {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    interface LCD {
        void write(String[] s);
        void read(String[] s);
    }

    static class Core implements LCD {
        @Override
        public void write(String[] s) {
            System.out.print("INPUT: ");
            try {
                s[0] = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void read(String[] s) {
            System.out.println("Output: " + s[0]);
        }
    }

    static class Decorator implements LCD {
        private LCD inner;
        public Decorator(LCD inner) {
            this.inner = inner;
        }

        @Override
        public void write(String[] s) {
            inner.write(s);
        }

        @Override
        public void read(String[] s) {
            inner.read(s);
        }
    }

    static class Authenticate extends Decorator {
        public Authenticate(LCD inner) {
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
    }

    static class Scramble extends Decorator {
        public Scramble(LCD inner) {
            super(inner);
        }

        @Override
        public void write(String[] s) {
            super.write(s);
            System.out.println("encrypt: ");
            StringBuffer sb = new StringBuffer(s[0]);
            for (int i = 0; i < sb.length(); i++)
                sb.setCharAt(i, (char)(sb.charAt(i) - 5));
            s[0] = sb.toString();
        }

        @Override
        public void read(String[] s) {
            StringBuffer sb = new StringBuffer(s[0]);
            for (int i = 0; i < sb.length(); i++)
                sb.setCharAt(i, (char)(sb.charAt(i) + 5));
            s[0] = sb.toString();
            System.out.println("decrypt:");
            super.read(s);
        }
    }

    public static void main(String[] args) {
        //  authenticate, input, encrypt, authenticate, decrypt, output
        LCD stream = new Authenticate(new Scramble(new Core()));

        String[] s = {new String()};
        stream.write(s);
        System.out.println("main: " + s[0]);
        stream.read(s);
    }

}
