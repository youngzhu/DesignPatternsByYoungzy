package sourcemaking.creational.builder.after;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public interface Builder {
    void setWidthHeight(int width, int height);
    void buildCell(String value);
    void startNewRow();

    Component getResult();
}

class JTableBuilder implements Builder {
    private JTable table;
    private TableModel model;
    private int x = 0, y = 0;

    @Override
    public void setWidthHeight(int width, int height) {
        table = new JTable(height, width);
        model = table.getModel();
    }

    @Override
    public void buildCell(String value) {
        model.setValueAt(value, y, x++);

    }

    @Override
    public void startNewRow() {
        x = 0;
        y++;
    }

    @Override
    public Component getResult() {
        return table;
    }
}

class GridLayoutBuilder implements Builder {
    private JPanel panel = new JPanel();

    @Override
    public void setWidthHeight(int width, int height) {
        panel.setLayout(new GridLayout(height, width));
        panel.setBackground(Color.white);
    }

    @Override
    public void buildCell(String value) {
        panel.add(new Label(value));
    }

    @Override
    public void startNewRow() {

    }

    @Override
    public Component getResult() {
        return panel;
    }
}

class GridBagLayoutBuilder implements Builder {
    private JPanel panel = new JPanel();
    private GridBagConstraints constraints = new GridBagConstraints();
    private int x = 0, y = 0;

    @Override
    public void setWidthHeight(int width, int height) {
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.white);

    }

    @Override
    public void buildCell(String value) {
        constraints.gridx = x++;
        constraints.gridy = y;
        panel.add(new Label(value), constraints);
    }

    @Override
    public void startNewRow() {
        x = 0;
        y++;
    }

    @Override
    public Component getResult() {
        return panel;
    }
}