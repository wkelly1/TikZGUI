package org.tikzgui.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.*;
import javafx.event.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable{
    private double maxWidth = 0.0;
    private double maxHeight = 0.0;


    @FXML
    private Canvas canvas;

    @FXML
    public GraphicsContext gc;

    @FXML
    public ScrollPane canvasParent;

    public void initGraphics()
    {
        gc = canvas.getGraphicsContext2D();
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        canvas.setWidth(canvasParent.widthProperty().doubleValue());
        canvas.setHeight(canvasParent.heightProperty().doubleValue());

        canvasParent.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode().getCode() == 32 && !canvasParent.isPannable()) {
                canvasParent.setPannable(true);

            }
        });

        canvasParent.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (keyEvent.getCode().getCode() == 32 && canvasParent.isPannable()) {
                canvasParent.setPannable(false);

            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (!canvasParent.isPannable()) {
                gc.fillRect(event.getX(), event.getY(), 10.0, 10.0);
            }
        });

        canvasParent.widthProperty().addListener((ov, oldValue, newValue) -> {

            if (newValue.doubleValue() > this.maxWidth) {
                canvas.setWidth(newValue.doubleValue());
                this.maxWidth = newValue.doubleValue();
            }
        });

        canvasParent.heightProperty().addListener((ov, oldValue, newValue) -> {

            if (newValue.doubleValue() > this.maxHeight) {
                canvas.setHeight(newValue.doubleValue());
                this.maxHeight = newValue.doubleValue();
            }
        });


        initGraphics();
    }  
}
