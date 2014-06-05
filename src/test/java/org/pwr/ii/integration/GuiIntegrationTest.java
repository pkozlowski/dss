package org.pwr.ii.integration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.pwr.ii.gui.ComponentProvider;

import java.io.IOException;

/**
 * Created by Daniel Karwacki on 2014-06-05.
 */
public class GuiIntegrationTest extends GuiTest {

    @Test
    public void should() throws IOException {
        doubleClick(ComponentProvider.PRICE_MINIMUM).type(KeyCode.DELETE).type("10");
        doubleClick(ComponentProvider.PRICE_MAXIMUM).type(KeyCode.DELETE).type("20");
        drag(ComponentProvider.PRICE_SLIDER).by(60, 0).drop();

        doubleClick(ComponentProvider.VOLTAGE_MINIMUM).type(KeyCode.DELETE).type("30");
        doubleClick(ComponentProvider.VOLTAGE_MAXIMUM).type(KeyCode.DELETE).type("50");
        drag(ComponentProvider.VOLTAGE_SLIDER).by(-20, 0).drop();

        click(ComponentProvider.COLOR_PICKER).click();
        drag(ComponentProvider.COLOR_SLIDER).by(-60, 0).drop();

        doubleClick(ComponentProvider.SIZE_FIELD).type(KeyCode.DELETE).type("750");
        drag(ComponentProvider.SIZE_SLIDER).by(50, 0).drop();
        click(ComponentProvider.FIND_BUTTON);

        move(ComponentProvider.ACCORDION_LIST);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Parent getRootNode() {
        try {
            Parent root = FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource("main.fxml"));

            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
