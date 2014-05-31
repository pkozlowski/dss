package org.pwr.ii.gui.components;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Author: Piotr Kozłowski
 * Date: 2014-05-31.
 */

public class NumericEventFilter implements EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent t) {
        char ar[] = t.getCharacter().toCharArray();
        char ch = ar[t.getCharacter().toCharArray().length - 1];
        if (!(ch >= '0' && ch <= '9'))
            t.consume();
    }

}
