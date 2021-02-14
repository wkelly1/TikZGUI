package org.tikzgui.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.tikzgui.core.Property;
import org.tikzgui.core.PropertySet;

public class PropertySetPanel extends VBox {
    private PropertySet propertySet;

    public PropertySetPanel(PropertySet propertySet){
        this.propertySet = propertySet;

        for (Property property : propertySet.getProperties()){
            Label label = new Label(property.toString());
            this.getChildren().add(label);
        }
    }

}
