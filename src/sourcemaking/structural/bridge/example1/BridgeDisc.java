package sourcemaking.structural.bridge.example1;

import java.util.Random;

public class BridgeDisc {
    public static void main(String[] args) {
        StackArray[] stackArrays = {new StackArray(), new StackFIFO(), new StackHanoi()};
        StackList stackList = new StackList();

        for (int i = 1, num; i < 15; i++) {
            stackArrays[0].push(i);
            stackList.push(i);
            stackArrays[1].push(i);
        }

        Random random = new Random();
        for (int i = 1, num; i < 15; i++) {
            stackArrays[2].push(random.nextInt(20));
        }

        while (! stackArrays[0].isEmpty()) {
            System.out.print(stackArrays[0].pop() + " ");
        }
        System.out.println();

        while (! stackList.isEmpty()) {
            System.out.print(stackList.pop() + " ");
        }
        System.out.println();

        while (! stackArrays[1].isEmpty()) {
            System.out.print(stackArrays[1].pop() + " ");
        }
        System.out.println();

        while (! stackArrays[2].isEmpty()) {
            System.out.print(stackArrays[2].pop() + " ");
        }
        System.out.println();

        System.out.println("total rejected is " +
                ((StackHanoi) stackArrays[2]).reportRejected());
    }
}
