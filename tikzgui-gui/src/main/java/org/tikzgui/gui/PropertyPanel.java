package org.tikzgui.gui;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import org.tikzgui.core.PropertySet;
import org.tikzgui.guishapes.Shape;

public class PropertyPanel extends VBox {


    ObservableList<Shape> selectedList;

    private ListChangeListener<? super Shape> selectedListener = ((change) -> {

        if (selectedList.size() == 1){
            drawPanel();
        } else {
            clearPanel();
        }
    });

    private void clearPanel() {
        this.getChildren().removeAll(this.getChildren());
    }

    private void drawPanel(){
        Shape currentShape = selectedList.get(0);
        System.out.println(currentShape.getGuiElement());
        for (PropertySet ps : currentShape.getGuiElement().getProperties()){
            PropertySetPanel panel = new PropertySetPanel(ps, currentShape.getUpdate());
            this.getChildren().add(panel);
        }
    }

    public PropertyPanel(){

    }

    public void setSelectedList(ObservableList<Shape> selectedList) {
        if (this.selectedList != null){
            this.selectedList.removeListener(selectedListener);
        }
        this.selectedList = selectedList;
        this.selectedList.addListener(selectedListener);

    }
}
