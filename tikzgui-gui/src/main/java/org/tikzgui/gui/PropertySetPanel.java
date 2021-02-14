package org.tikzgui.gui;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.tikzgui.core.EllipseProps;
import org.tikzgui.core.Property;
import org.tikzgui.core.PropertySet;
import org.tikzgui.core.Stroke;

public class PropertySetPanel extends VBox {
    private PropertySet propertySet;

    public PropertySetPanel(PropertySet propertySet, Runnable update){
        this.propertySet = propertySet;

        for (Property<?> property : propertySet.getProperties()){
        	PropertyControlFactory pcf = new PropertyControlFactory();
        	PropertyControl<?> propertyControl = pcf.getPropertyControl(property, update);
            this.getChildren().add(propertyControl.getContents());
        }
    }

}
