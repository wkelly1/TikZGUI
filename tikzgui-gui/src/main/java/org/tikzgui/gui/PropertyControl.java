package org.tikzgui.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.Node;

import org.tikzgui.core.Property;


public class PropertyControl<T> {
	protected HBox container = new HBox();
	private Label label;
	protected Property<T> property;
	protected Runnable update;

	public PropertyControl(Property<T> property, Runnable update) {
		label = new Label(property.getName());
		container.getChildren().add(label);
		this.property = property;
		this.update = update;
	}
	
	public Node getContents() {
		return container;
	}
}
