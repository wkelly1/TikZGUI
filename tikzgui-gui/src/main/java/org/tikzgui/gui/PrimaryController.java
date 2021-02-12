package org.tikzgui.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.canvas.*;

public class PrimaryController {
    @FXML
    private Canvas canvas;
    
    

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
