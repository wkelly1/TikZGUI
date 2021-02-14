package org.tikzgui.gui;

import org.tikzgui.core.Property;

import javafx.scene.control.Label;

public class GenericPropertyControl<T> extends PropertyControl<T> {
	private Label label;
	
	public GenericPropertyControl(Property<T> property) {
		super(property);
		label = new Label(property.getValueString());
		this.container.getChildren().add(label);
	}
}
