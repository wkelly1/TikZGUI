package org.tikzgui.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.Node;

import org.tikzgui.core.Property;


public class PropertyControl<T> {
	protected HBox container = new HBox();
	private Label label;
	
	public PropertyControl(Property<T> property) {
		label = new Label(property.getName());
		container.getChildren().add(label);
	}
	
	public Node getContents() {
		return container;
	}
}
