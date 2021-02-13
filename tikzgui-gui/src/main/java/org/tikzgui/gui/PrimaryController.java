package org.tikzgui.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import org.tikzgui.core.PictureContainer;
import org.tikzgui.core.Point;
import org.tikzgui.core.Rectangle;
import org.tikzgui.guishapes.GuiRectangle;
import org.tikzgui.texgen.TexGenerator;

import java.net.URL;
import java.util.ArrayList;
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

    private ArrayList<Shape> elements = new ArrayList<>();


    public void initGraphics() {

        canvas.setPrefWidth(canvasInitWidth);
        canvas.setPrefHeight(canvasInitHeight);

        canvasParent.setHvalue(canvasInitHeight / 2);
        canvasParent.setVvalue(canvasInitHeight / 2);
        canvasParent.setHmax(canvasInitWidth);
        canvasParent.setVmax(canvasInitWidth);
        System.out.println(canvasParent.getHmax());


        ToolbarButton pointer = new ToolbarButton("POINTER", getClass().getResource("icons/pointer.png").toExternalForm(), true, Cursor.DEFAULT, canvasParent);
        pointer.addActionHandler(() -> {
            setCanDrag(false);
            canvasParent.setCursor(Cursor.DEFAULT);
        });

        ToolbarButton shape = new ToolbarButton("SHAPE", getClass().getResource("icons/plus.png").toExternalForm(), true, Cursor.DEFAULT, canvasParent);
        shape.addActionHandler(() -> {
            setCanDrag(false);
            canvasParent.setCursor(Cursor.CROSSHAIR);
        });
        ToolbarButton pan = new ToolbarButton("PAN", getClass().getResource("icons/pan.png").toExternalForm(), true, Cursor.HAND, canvasParent);
        pan.addActionHandler(() -> {
            setCanDrag(true);
            canvasParent.setCursor(Cursor.DEFAULT);
        });

        ToolbarToggle zoomIn = new ToolbarToggle(getClass().getResource("icons/zoomIn.png").toExternalForm(), true);
        ToolbarToggle zoomOut = new ToolbarToggle(getClass().getResource("icons/zoomOut.png").toExternalForm(), true);
        ToolbarToggle export = new ToolbarToggle("Export", false);
        zoomIn.addActionHandler(() -> {
            canvas.setScaleX(canvas.getScaleX() + 0.1);
            canvas.setScaleY(canvas.getScaleY() + 0.1);
        });
        zoomOut.addActionHandler(() -> {
            canvas.setScaleX(canvas.getScaleX() - 0.1);
            canvas.setScaleY(canvas.getScaleY() - 0.1);
        });
        export.addActionHandler(() -> {
            TexGenerator generator = new TexGenerator(rootContainer);
            String tex = generator.generate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("TikZ Output");
            alert.setHeaderText("TikZ Output");
            alert.setContentText(tex);

            alert.showAndWait();
        });
        ToolbarButton[] btnsLeft = {pointer, shape, pan};
        ToolbarToggle[] btnsRight = {zoomOut, zoomIn, export};

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

    private void updateLayers(){
        leftBar.getChildren().removeAll(leftBar.getChildren());

        for (int i=1; i<elements.size()+1; i++){
            final Node elem = elements.get(i-1);
            HBox panelItem = createLayerPanelItem("Rectangle "+ i );
            panelItem.setOnMouseClicked((MouseEvent e) -> {
                if (!((GuiRectangle) elem).isSelected()){
                    ((GuiRectangle) elem).select();
                }
            });


            leftBar.getChildren().add(panelItem);
        }
    }

    private HBox createLayerPanelItem(String text){
        HBox hbox = new HBox();
        Label label = new Label(text);
        label.setCursor(Cursor.HAND);
        HBox.setHgrow(hbox, Priority.ALWAYS);
        hbox.getChildren().add(label);
        hbox.getStyleClass().add("layer");
        return hbox;
    }

    private void deleteShape(Node shape){
        getSelected().forEach(node -> {
            ((GuiRectangle) node).delete();
            elements.remove(node);
        });

        updateLayers();
    }

    private void setSelected(Node element) {

        removeSelected();
        properties.getChildren().add(new Label(element.getClass().toString()));
    }

    private void removeSelected() {
        getSelected().forEach(node -> {
            System.out.println("UNSELECT");
            ((GuiRectangle) node).unselect();

        });
    }

    private ArrayList<Node> getSelected(){
        ArrayList<Node> selected = new ArrayList<>();
        elements.forEach(node -> {
            if (((GuiRectangle) node).isSelected()){
                selected.add(node);
            }
        });
        return selected;
    }




    private void initializeShapeDraw(Pane parent) {
        // Shape drawing
        parent.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            if (tb.getAction().equals("SHAPE")) {
//                javafx.scene.shape.Rectangle rect1 = new javafx.scene.shape.Rectangle(event.getX(), event.getY(), 1, 1);
                GuiRectangle rect = new GuiRectangle(event.getX(), event.getY(), 1, 1, parent);


                rect.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        if (tb.getAction().equals("POINTER")) {

                            if (!rect.isSelected()) {
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
                            t.consume();
                        }
                    }
                });


                rect.setFill(null);
                rect.setStroke(Color.BLACK);
                rect.setStrokeWidth(5);
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
                elements.add(rect);
            }
        });

        canvasParent.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (tb.getAction().equals("POINTER")) {
                removeSelected();
            }
        });

        parent.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
            if (currentNode != null) {
                double currentWidth = Math.abs(event.getX() - currentNodeX);
                double currentHeight = Math.abs(currentNodeY - event.getY());
                double xPos = Math.min(event.getX(), currentNodeX);
                double yPos = Math.min(event.getY(), currentNodeY);
                ((javafx.scene.shape.Rectangle) currentNode).setWidth(currentWidth);
                ((javafx.scene.shape.Rectangle) currentNode).setHeight(currentHeight);
                ((javafx.scene.shape.Rectangle) currentNode).setX(xPos);
                ((javafx.scene.shape.Rectangle) currentNode).setY(yPos);

            }
        });

        parent.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent event) -> {
            Rectangle rect = new Rectangle(new Point(event.getX() / 10, event.getY() / 10), new Point((event.getX() + 10) / 10, (event.getY() + 10) / 10), rootContainer);
            rootContainer.addChild(rect);
            currentNode = null;
            updateLayers();
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

            if (keyEvent.getCode() == KeyCode.DELETE){
                getSelected().forEach(this::deleteShape);

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
