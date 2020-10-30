package sourcemaking.creational.builder.before;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

class JTableTable {
    private JTable table;

    public JTableTable(String[][] matrix) {
        table = new JTable(matrix[0].length, matrix.length);

        TableModel model = table.getModel();
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                model.setValueAt(matrix[i][j], j, i);
            }
        }
    }

    public Component getTable() {
        return table;
    }
}

class GridLayoutTable {
    private JPanel table = new JPanel();

    public GridLayoutTable(String[][] matrix) {
        table.setLayout(new GridLayout(matrix[0].length, matrix.length));
        table.setBackground(Color.white);

        for (int i = 0; i < matrix[0].length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                table.add(new Label(matrix[j][i]));
            }
        }
    }

    public Component getTable() {
        return table;
    }
}

class GridBagLayoutTable {
    private JPanel table = new JPanel();

    public GridBagLayoutTable(String[][] matrix) {
        GridBagConstraints constraints = new GridBagConstraints();

        table.setLayout(new GridBagLayout());
        table.setBackground(Color.white);

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                constraints.gridx = i;
                constraints.gridy = j;
                table.add(new Label(matrix[i][j]), constraints);
            }
        }
    }

    public Component getTable() {
        return table;
    }
}
