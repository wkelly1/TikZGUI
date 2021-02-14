package org.tikzgui.gui;

import org.tikzgui.core.DimProperty;
import org.tikzgui.core.Property;
import javafx.scene.Node;
import javafx.scene.control.Control;

public class PropertyControlFactory {

	public PropertyControlFactory() {
		
	}
	
	public <T> PropertyControl<?> getPropertyControl(Property<T> property) {
		if(property instanceof DimProperty) 
			return new DimPropertyControl((DimProperty)property); 
		else
			return new GenericPropertyControl<T>(property);
	}
}
