package org.tikzgui.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.*;
import javafx.event.*;
import javafx.scene.control.ScrollPane;
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
    private void canvasClick(MouseEvent event) {
        System.out.println("sdfjlkd");
        canvas.getGraphicsContext2D().fillRect(75,75,100,100);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            System.out.println(event);
            gc.fillRect(event.getX(), event.getY(), 10.0, 10.0);
        });

        canvasParent.widthProperty().addListener((ov, oldValue, newValue) -> {

            if (newValue.doubleValue() > this.maxWidth) {
                System.out.println("resizing");
                canvas.setWidth(newValue.doubleValue());
                this.maxWidth = newValue.doubleValue();
            }
        });

        canvasParent.heightProperty().addListener((ov, oldValue, newValue) -> {

            if (newValue.doubleValue() > this.maxHeight) {
                System.out.println("resizing");
                canvas.setHeight(newValue.doubleValue());
                this.maxHeight = newValue.doubleValue();
            }
        });


        initGraphics();
    }  
}
