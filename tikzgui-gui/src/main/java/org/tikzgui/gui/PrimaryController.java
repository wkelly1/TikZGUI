package org.tikzgui.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.canvas.*;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable{
    private double maxWidth = 0.0;
    private double maxHeight = 0.0;
    private boolean pan = false;

    final private double canvasInitWidth = 5000.0;
    final private double canvasInitHeight = 5000.0;

    private KeyEvent currentKeyPressed;


    @FXML
    private Canvas canvas;

    @FXML
    public GraphicsContext gc;

    @FXML
    public ScrollPane canvasParent;

    @FXML
    public Button panBtn;

    @FXML
    public Button squareBtn;

    public void initGraphics()
    {
        canvas.setWidth(canvasInitWidth);
        canvas.setHeight(canvasInitHeight);

        canvasParent.setHvalue(canvasInitHeight / 2);
        canvasParent.setVvalue(canvasInitHeight / 2);
        canvasParent.setHmax(canvasInitWidth);
        canvasParent.setVmax(canvasInitWidth);
        System.out.println(canvasParent.getHmax());
        gc = canvas.getGraphicsContext2D();
    }


    @FXML
    private void zoomIn() throws IOException {
        canvas.setScaleX(canvas.getScaleX() + 0.1);
        canvas.setScaleY(canvas.getScaleY() + 0.1);
    }

    @FXML
    private void zoomOut() throws IOException {
        canvas.setScaleX(canvas.getScaleX() - 0.1);
        canvas.setScaleY(canvas.getScaleY() - 0.1);
    }

    @FXML
    private void onPan() throws IOException {
        if (!this.pan) {
            panBtn.setStyle("-fx-background-color: #5684DF");
            squareBtn.setStyle("-fx-background-color: transparent");
            setCanDrag(true);
        }
    }

    @FXML
    private void onSquare() throws IOException {
        if (this.pan) {
            squareBtn.setStyle("-fx-background-color: #5684DF");
            panBtn.setStyle("-fx-background-color: transparent");
            canvasParent.setPannable(false);
            this.pan = false;
            canvasParent.setCursor(Cursor.DEFAULT);
        }
    }

    private void setCanDrag(boolean value) {
        if (!value){
            canvasParent.setPannable(false);
            this.pan = false;
            canvasParent.setCursor(Cursor.DEFAULT);
        } else {
            canvasParent.setPannable(true);
            this.pan = true;
            canvasParent.setCursor(Cursor.HAND);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        canvasParent.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (this.currentKeyPressed == null) {
                this.currentKeyPressed = keyEvent;
            }
        });

        canvasParent.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (this.currentKeyPressed != null) {
                this.currentKeyPressed = null;
            }
        });

        canvasParent.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.P && this.pan) {
                setCanDrag(true);
            } else if (keyEvent.getCode() == KeyCode.P && !this.pan) {
                setCanDrag(false);
            }
        });

        canvasParent.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.MIDDLE) {
                setCanDrag(true);
            }
        });

        canvasParent.addEventFilter(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.MIDDLE) {
                setCanDrag(false);
            }
        });

        canvasParent.addEventFilter(ScrollEvent.SCROLL, scrollEvent -> {
            if (this.currentKeyPressed != null) {

                if (this.currentKeyPressed.getCode().getName().equals("Ctrl")){
                    scrollEvent.consume();
                    if (scrollEvent.getDeltaY() > 0) {
                        canvas.setScaleX(canvas.getScaleX() + 0.1);
                        canvas.setScaleY(canvas.getScaleY() + 0.1);
                    } else if (scrollEvent.getDeltaY() < 0) {
                        canvas.setScaleX(canvas.getScaleX() - 0.1);
                        canvas.setScaleY(canvas.getScaleY() - 0.1);
                    }
                }
            }
        });





        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (!canvasParent.isPannable()) {
                gc.fillRect(event.getX(), event.getY(), 10.0, 10.0);
            }
        });

        canvasParent.widthProperty().addListener((ov, oldValue, newValue) -> {

            if (newValue.doubleValue() > this.maxWidth && newValue.doubleValue() > this.canvasInitWidth) {
                System.out.println("resizeW");
                canvas.setWidth(newValue.doubleValue());
                this.maxWidth = newValue.doubleValue();
            }
        });

        canvasParent.heightProperty().addListener((ov, oldValue, newValue) -> {

            if (newValue.doubleValue() > this.maxHeight && newValue.doubleValue() > this.canvasInitHeight) {
                System.out.println("resizeH");
                canvas.setHeight(newValue.doubleValue());
                this.maxHeight = newValue.doubleValue();
            }
        });


        initGraphics();
    }  
}
