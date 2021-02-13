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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import org.tikzgui.core.PictureContainer;
import org.tikzgui.core.Point;
import org.tikzgui.core.Rectangle;
import org.tikzgui.guishapes.GuiRectangle;
import org.tikzgui.texgen.TexGenerator;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    private double maxWidth = 0.0;
    private double maxHeight = 0.0;

    final private double canvasInitWidth = 5000.0;
    final private double canvasInitHeight = 5000.0;

    private Shape currentNode;
    private double currentNodeX;
    private double currentNodeY;

    private int shapeIndex = 1;

    private KeyEvent currentKeyPressed;

    private PictureContainer rootContainer = new PictureContainer();

    private Node selected;
//    private String currentAction;

    @FXML
    private VBox leftBar;

    @FXML
    private VBox properties;

    @FXML
    private AnchorPane canvas;

    @FXML
    private BorderPane layout;

    @FXML
    public ScrollPane canvasParent;

    private Toolbar tb;

    public void initGraphics() {

        canvas.setPrefWidth(canvasInitWidth);
        canvas.setPrefHeight(canvasInitHeight);

        canvasParent.setHvalue(canvasInitHeight / 2);
        canvasParent.setVvalue(canvasInitHeight / 2);
        canvasParent.setHmax(canvasInitWidth);
        canvasParent.setVmax(canvasInitWidth);
        System.out.println(canvasParent.getHmax());


        ToolbarButton pointer = new ToolbarButton("POINTER", "Pointer", false, Cursor.DEFAULT, canvasParent);
        pointer.addActionHandler(() -> {
            setCanDrag(false);
        });

        ToolbarButton shape = new ToolbarButton("SHAPE", getClass().getResource("icons/plus.png").toExternalForm(), true, Cursor.DEFAULT, canvasParent);
        shape.addActionHandler(() -> {
            setCanDrag(false);
        });
        ToolbarButton pan = new ToolbarButton("PAN", getClass().getResource("icons/pan.png").toExternalForm(), true, Cursor.HAND, canvasParent);
        pan.addActionHandler(() -> {
            setCanDrag(true);
        });
        ToolbarButton[] btnsLeft = {pointer, shape, pan};
        ToolbarButton[] btnsRight = {};

        tb = new Toolbar(btnsLeft, btnsRight);
        tb.setAction("POINTER");
        this.layout.topProperty().setValue(tb);

    }

    @FXML
    private void onExport() throws IOException {
//        Stage stage = new Stage();
//        Scene scene = new Scene(loadFXML("secondary"), 400, 400);
//        stage.setTitle("TikZGUI");
//        scene.getStylesheets().add(getClass().getResource("css/main.css").toExternalForm());
//        stage.setScene(scene);
//        stage.show();

        TexGenerator generator = new TexGenerator(rootContainer);
        System.out.println(generator.generate());
    }

    private void setAction(String action) {
        if (action.equals("PAN")) {
            this.tb.setAction("PAN");
            setCanDrag(true);
        }
        if (action.equals("SHAPE")) {
            this.tb.setAction("SHAPE");
            setCanDrag(false);
        }
        if (action.equals("POINTER")) {
            this.tb.setAction("POINTER");
            setCanDrag(false);
        }
    }

    private void setCanDrag(boolean value) {
        canvasParent.setPannable(value);
    }

    private void initializeShapeEdit(Node parent){

    }

    private void setSelected(Node element){
        this.selected = element;
        removeSelected();
        properties.getChildren().add(new Label(element.getClass().toString()));
    }

    private void removeSelected(){
        properties.getChildren().removeAll(properties.getChildren());
    }



    private void initializeShapeDraw(Pane parent){
        // Shape drawing
        parent.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            if (tb.getAction().equals("SHAPE")) {
//                javafx.scene.shape.Rectangle rect1 = new javafx.scene.shape.Rectangle(event.getX(), event.getY(), 1, 1);
                GuiRectangle rect = new GuiRectangle(event.getX(), event.getY(), 1, 1, parent);


                rect.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        if (tb.getAction().equals("POINTER")) {

                            if (!rect.isSelected()){
                                rect.setHover();
                            }
                        }
                    }
                });

                rect.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        if (tb.getAction().equals("POINTER")) {
                            if (!rect.isSelected()) {
                                rect.removeHover();
                            }
                        }
                    }
                });
                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if (tb.getAction().equals("POINTER")) {
                            if (!rect.isSelected()) {
                                rect.select();
                                setSelected(rect);
                            } else {
                                rect.unselect();
                                removeSelected();
                            }
//                            rect.setStroke(Color.GREEN);
//                            rect.setStrokeWidth(2);
                            t.consume();
                        }
                    }
                });


                rect.setFill(Color.TRANSPARENT);
                rect.setStroke(Color.BLACK);
                HBox hbox = new HBox();
                Label lbl = new Label("Rectangle " + shapeIndex);

                lbl.getStyleClass().add("layer");
                hbox.getChildren().add(lbl);

                leftBar.getChildren().add(lbl);
                shapeIndex++;
                currentNode = rect;
                currentNodeX = rect.getX();
                currentNodeY = rect.getY();
                canvas.getChildren().add(rect);


            }
        });

        canvasParent.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (tb.getAction().equals("POINTER")){
                removeSelected();
            }
        });

        parent.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
            if (currentNode != null) {
                if (event.getX() >= currentNodeX) {
                    if (event.getX() <= currentNodeX) {

                        ((javafx.scene.shape.Rectangle) currentNode).setX(event.getX());
                        ((javafx.scene.shape.Rectangle) currentNode).setWidth((currentNodeX - event.getX()));
                    } else {
                        ((javafx.scene.shape.Rectangle) currentNode).setWidth((event.getX() - currentNodeX));

                    }
                } else if (event.getX() < currentNodeX) {
                    ((javafx.scene.shape.Rectangle) currentNode).setWidth(currentNodeX - event.getX());
                    ((javafx.scene.shape.Rectangle) currentNode).setX(event.getX());
                }

                if (event.getY() >= currentNodeY) {
                    if (event.getY() <= currentNodeY) {

                        ((javafx.scene.shape.Rectangle) currentNode).setY(event.getY());
                        ((javafx.scene.shape.Rectangle) currentNode).setHeight(currentNodeY - event.getY());
                    } else {
                        ((javafx.scene.shape.Rectangle) currentNode).setHeight(event.getY() - currentNodeY);

                    }
                } else if (event.getY() < currentNodeY) {
                    ((javafx.scene.shape.Rectangle) currentNode).setHeight(currentNodeY - event.getY());
                    ((javafx.scene.shape.Rectangle) currentNode).setY(event.getY());
                }
            }
        });

        parent.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent event) -> {
            Rectangle rect = new Rectangle(new Point(event.getX() / 10, event.getY() / 10), new Point((event.getX() + 10) / 10, (event.getY() + 10) / 10), rootContainer);
            rootContainer.addChild(rect);
            currentNode = null;
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // KEY BINDINGS
        canvasParent.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.P) {
                tb.setAction("PAN");
            }

            if (keyEvent.getCode() == KeyCode.V) {
                tb.setAction("POINTER");
            }

            if (keyEvent.getCode() == KeyCode.S) {
                tb.setAction("SHAPE");
            }
        });


        // Store for multi key press
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


        // Middle mouse button panning
        canvasParent.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.MIDDLE) {
                if (!tb.getAction().equals("PAN")) {
                    tb.setActionTemp("PAN");
                }
            }
        });

        canvasParent.addEventFilter(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.MIDDLE) {
                setCanDrag(false);
                tb.removeTempAction();
            }
        });

        // Ctrl Scroll zooming
        canvasParent.addEventFilter(ScrollEvent.SCROLL, scrollEvent -> {
            if (this.currentKeyPressed != null) {

                if (this.currentKeyPressed.getCode().getName().equals("Ctrl")) {
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

        initializeShapeDraw(canvas);


        // Window resizing
        canvasParent.widthProperty().addListener((ov, oldValue, newValue) -> {

            if (newValue.doubleValue() > this.maxWidth && newValue.doubleValue() > this.canvasInitWidth) {

                canvas.setPrefWidth(newValue.doubleValue());
                this.maxWidth = newValue.doubleValue();
            }
        });

        canvasParent.heightProperty().addListener((ov, oldValue, newValue) -> {

            if (newValue.doubleValue() > this.maxHeight && newValue.doubleValue() > this.canvasInitHeight) {

                canvas.setPrefHeight(newValue.doubleValue());
                this.maxHeight = newValue.doubleValue();
            }
        });


        initGraphics();
    }
}
