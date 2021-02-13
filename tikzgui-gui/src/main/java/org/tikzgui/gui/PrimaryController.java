package org.tikzgui.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import org.tikzgui.core.PictureContainer;
import org.tikzgui.core.Point;
import org.tikzgui.core.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable{
    private double maxWidth = 0.0;
    private double maxHeight = 0.0;
    private boolean pan = false;

    final private double canvasInitWidth = 5000.0;
    final private double canvasInitHeight = 5000.0;

    private Shape currentNode;
    private double currentNodeX;
    private double currentNodeY;

    private int shapeIndex = 1;

    private KeyEvent currentKeyPressed;

    private PictureContainer rootContainer = new PictureContainer();

    @FXML
    private VBox leftBar;

    @FXML
    private AnchorPane canvas;

    @FXML
    public ScrollPane canvasParent;

    @FXML
    public Button panBtn;

    @FXML
    public Button squareBtn;

    public void initGraphics()
    {

        canvas.setPrefWidth(canvasInitWidth);
        canvas.setPrefHeight(canvasInitHeight);

        canvasParent.setHvalue(canvasInitHeight / 2);
        canvasParent.setVvalue(canvasInitHeight / 2);
        canvasParent.setHmax(canvasInitWidth);
        canvasParent.setVmax(canvasInitWidth);
        System.out.println(canvasParent.getHmax());

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

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    @FXML
    private void onExport() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML("secondary"), 400, 400);
        stage.setTitle("TikZGUI");
        scene.getStylesheets().add(getClass().getResource("css/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
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

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            if (!canvasParent.isPannable()) {
                javafx.scene.shape.Rectangle rect1 = new javafx.scene.shape.Rectangle(event.getX(), event.getY(), 1, 1);
                rect1.fillProperty().setValue(null);
                rect1.setStroke(Color.BLACK);
                HBox hbox = new HBox();
                Label lbl = new Label("Rectangle " + shapeIndex);

                lbl.getStyleClass().add("layer");
                hbox.getChildren().add(lbl);

                leftBar.getChildren().add(lbl);
                shapeIndex ++;
                currentNode = rect1;
                currentNodeX = rect1.getX();
                currentNodeY = rect1.getY();
                canvas.getChildren().add(rect1);
//                gc.fillRect(event.getX(), event.getY(), 10.0, 10.0);
                Rectangle rect = new Rectangle(new Point(event.getX(), event.getY()), new Point(event.getX() + 1, event.getY() + 1), rootContainer);
                rootContainer.addChild(rect);
                for (int i=0; i<rootContainer.getChildren().length; i++){
                    System.out.println(((Rectangle) rootContainer.getChildren()[i]).getPointA().toString());
                }

            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
            if (currentNode != null){
                if (event.getX() >= currentNodeX){
                    if (event.getX() <= currentNodeX){
                        System.out.println("1");
                        ((javafx.scene.shape.Rectangle) currentNode).setX(event.getX());
                        ((javafx.scene.shape.Rectangle) currentNode).setWidth((currentNodeX - event.getX()));
                    }else {
                        ((javafx.scene.shape.Rectangle) currentNode).setWidth((event.getX() - currentNodeX));

                    }
                } else if (event.getX() < currentNodeX){
                    ((javafx.scene.shape.Rectangle) currentNode).setWidth(currentNodeX - event.getX());
                    ((javafx.scene.shape.Rectangle) currentNode).setX(event.getX());
                }

                if (event.getY() >= currentNodeY){
                    if (event.getY() <= currentNodeY ){
                        System.out.println("2");
                        ((javafx.scene.shape.Rectangle) currentNode).setY(event.getY());
                        ((javafx.scene.shape.Rectangle) currentNode).setHeight(currentNodeY - event.getY());
                    } else {
                        ((javafx.scene.shape.Rectangle) currentNode).setHeight(event.getY() - currentNodeY);

                    }
                } else if (event.getY() < currentNodeY) {
                    ((javafx.scene.shape.Rectangle) currentNode).setHeight(currentNodeY - event.getY());
                    ((javafx.scene.shape.Rectangle) currentNode).setY(event.getY());
//                    ((javafx.scene.shape.Rectangle) currentNode).setWidth(y + event.getY());
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent event) -> {
            currentNode = null;
            System.out.println("done");
        });



        canvasParent.widthProperty().addListener((ov, oldValue, newValue) -> {

            if (newValue.doubleValue() > this.maxWidth && newValue.doubleValue() > this.canvasInitWidth) {
                System.out.println("resizeW");
                canvas.setPrefWidth(newValue.doubleValue());
                this.maxWidth = newValue.doubleValue();
            }
        });

        canvasParent.heightProperty().addListener((ov, oldValue, newValue) -> {

            if (newValue.doubleValue() > this.maxHeight && newValue.doubleValue() > this.canvasInitHeight) {
                System.out.println("resizeH");
                canvas.setPrefHeight(newValue.doubleValue());
                this.maxHeight = newValue.doubleValue();
            }
        });


        initGraphics();
    }  
}
