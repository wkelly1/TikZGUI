package org.tikzgui.gui;

import org.tikzgui.core.DimProperty;
import org.tikzgui.core.Property;
import javafx.scene.Node;
import javafx.scene.control.Control;

public class PropertyControlFactory {

	public PropertyControlFactory() {
		
	}
	
	public <T> PropertyControl<?> getPropertyControl(Property<T> property, Runnable update) {
		if(property instanceof DimProperty) 
			return new DimPropertyControl((DimProperty)property, update);
		else
			return new GenericPropertyControl<T>(property, update);
	}
}
