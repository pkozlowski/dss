package org.pwr.ii.gui.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.pwr.ii.criteria.Alcohol;
import org.pwr.ii.criteria.CriteriaCalculator;
import org.pwr.ii.criteria.Criterion;

import java.io.IOException;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-31.
 */
public class BottleTitledPane extends TitledPane {
    public BottleTitledPane(Alcohol alcohol, CriteriaCalculator calculator, double accordionWidth, double accordionHeight) {
        super();
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefHeight(accordionHeight);
        scroll.setPrefWidth(accordionWidth);

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 15, 5));
        initColumn(alcohol, calculator, grid);
        scroll.setContent(grid);
        this.setContent(scroll);
        this.setText(alcohol.getName());
    }

    private void initColumn(Alcohol alcohol, CriteriaCalculator calculator, GridPane grid) {
        setFirstColumn(alcohol, grid);
        setSecondColumn(alcohol, calculator, grid);
    }

    private void setSecondColumn(Alcohol alcohol, CriteriaCalculator calculator, GridPane grid) {

        int startRowNumber = 0;
        for (Criterion criterion : calculator.getCriteria()) {
            grid.add(new Label(criterion.getName()), 2, startRowNumber);
            grid.add(new Label(String.format("%.5f", criterion.calculatePartial(alcohol))), 3, startRowNumber++);
        }
        grid.add(getFancyLabel("General: "), 2, startRowNumber);
        grid.add(getFancyLabel(String.format("%.5f", calculator.calculate(alcohol))), 3, startRowNumber);
    }

    private void setFirstColumn(Alcohol alcohol, GridPane grid) {
        grid.add(new Label("Name: "), 0, 0);
        grid.add(new Label(alcohol.getName()), 1, 0);
        grid.add(new Label("Type: "), 0, 1);
        grid.add(new Label(alcohol.getTypeName()), 1, 1);
        grid.add(new Label("Price: "), 0, 2);
        grid.add(new Label(String.format("%.2f$", alcohol.getPrice())), 1, 2);
        grid.add(new Label("Size: "), 0, 3);
        grid.add(new Label(String.format("%.2fml", alcohol.getSize())), 1, 3);
        grid.add(new Label("Image: "), 0, 5);
        grid.add(new ImageView(alcohol.getImage()), 1, 5);

    }

    private Label getFancyLabel(String text) {
        Label t = new Label();
        t.setCache(true);
        t.setText(text);
        t.setFont(Font.font(null, FontWeight.BOLD, 20));
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        t.setEffect(r);
        t.setTranslateY(100);

        return t;
    }
}
