package sourcemaking.creational.builder.before;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class TableBuilderDemo {
    public static void main(String[] args) {
//        (new TableBuilderDemo()).demo(args);
        // 为了方便测试
        TableBuilderDemo demo = new TableBuilderDemo();

        demo.demo(new String[]{});
//        demo.demo(new String[]{"GridLayoutTable"});
//        demo.demo(new String[]{"GridBagLayout"});
    }

    /**
     * 客户端视角
     * @param args
     */
    public void demo(String[] args) {
        // Name of the GUI table class
        String className = args.length > 0 ? args[0] : "JTableTable";

        // Read data from file
        String fileName = getClass().getResource("../BuilderDemo.dat").getFile();
        String[][] matrix = readDataFile(fileName);

        Component component;
        if (className.equals("GridLayoutTable")) {
            component = new GridLayoutTable(matrix).getTable();
        } else if (className.equals("GridBagLayout")) {
            component = new GridBagLayoutTable(matrix).getTable();
        } else {
            component = new JTableTable(matrix).getTable();
        }

        JFrame frame = new JFrame("Builder Demo - " + className);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
    }

    private String[][] readDataFile(String fileName) {
        String[][] matrix = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            String[] cells;

            if ((line = br.readLine()) != null) {
                cells = line.split("\\t");
                int width = Integer.parseInt(cells[0]);
                int height = Integer.parseInt(cells[1]);
                matrix = new String[width][height];
            }

            int row = 0;
            while ((line = br.readLine()) != null) {
                cells = line.split("\\t");
                for (int i = 0; i < cells.length; ++i) {
                    matrix[i][row] = cells[i];
                }
                row++;
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matrix;
    }
}
