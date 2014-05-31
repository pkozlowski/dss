package org.pwr.ii.gui.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.pwr.ii.criteria.Alcohol;

import java.io.IOException;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-31.
 */
public class BottleTitledPane extends TitledPane {
    public BottleTitledPane(Alcohol alcohol) {
        super();
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Name: "), 0, 0);
        grid.add(new Label(alcohol.getName()), 1, 0);
        grid.add(new Label("Type: "), 0, 1);
        grid.add(new Label(alcohol.getTypeName()), 1, 1);
        grid.add(new Label("Price: "), 0, 2);
        grid.add(new Label(String.format("%.2f$", alcohol.getPrice())), 1, 2);
        grid.add(new Label("Size: "), 0, 3);
        grid.add(new Label(String.format("%.2fml", alcohol.getSize())), 1, 3);
        grid.add(new Label("Image: "), 0, 4);
        try {
            grid.add(new ImageView(alcohol.getImage()), 1, 4);
        } catch (IOException ignore) {
        }

        this.setContent(grid);
        this.setText(alcohol.getName());
    }
}
