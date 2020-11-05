package sourcemaking.creational.builder.after;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TableDirector {
    private Builder builder;

    public TableDirector(Builder builder) {
        this.builder = builder;
    }

    public void construct(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            String[] cells;

            if ((line = br.readLine()) != null) {
                cells = line.split("\\t");
                int width = Integer.parseInt(cells[0]);
                int height = Integer.parseInt(cells[1]);

                builder.setWidthHeight(width, height);
            }

            while ((line = br.readLine()) != null) {
                cells = line.split("\\t");
                for (int i = 0; i < cells.length; ++i) {
                    builder.buildCell(cells[i]);
                }
                builder.startNewRow();
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
