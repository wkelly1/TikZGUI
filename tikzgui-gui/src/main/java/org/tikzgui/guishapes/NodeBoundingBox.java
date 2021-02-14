package org.tikzgui.guishapes;

import javafx.scene.Group;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

public class NodeBoundingBox implements BoundingBox {
	private Bounds bounds;
	private Group box;
	private Rectangle rect;
	
	public NodeBoundingBox (Bounds bounds) {
		this.bounds = bounds;
		
		box = new Group(); 
		rect = new Rectangle(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
	}

	@Override
	public void calcOffset() {
		rect.setX(bounds.getMinX());
		rect.setY(bounds.getMinY());
		rect.setWidth(bounds.getWidth());
		rect.setHeight(bounds.getHeight());
	}

	@Override
	public Group getBox() {
		return this.box;
	}

}
