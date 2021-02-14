package org.tikzgui.guishapes;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import org.tikzgui.core.*;
import javafx.scene.layout.Pane;

public class NodeBoundingBox implements BoundingBox {
	private Bounds bounds;
	private Group box;
	private Rectangle rect;
	private GuiNode innerNode;

	public NodeBoundingBox (Bounds bounds, GuiNode innerNode, Pane parent) {
		this.bounds = bounds;
		this.innerNode = innerNode;
		box = new Group(); 
		rect = new Rectangle(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
		
		calcOffset();
		rect.setFill(Color.TRANSPARENT);
		rect.setStrokeWidth(2);
		rect.setStroke(Color.web("#18A0FB"));
		rect.setCursor(Cursor.MOVE);
		box.getChildren().add(rect);
//		parent.getChildren().add(box);
		new RectMover(parent, innerNode, rect, this);
	}

	@Override
	public void calcOffset() {
		rect.setX(innerNode.getBoundingX());
		rect.setY(innerNode.getBoundingY());
		rect.setWidth(bounds.getWidth());
		rect.setHeight(bounds.getHeight());
	}

	@Override
	public Group getBox() {
		System.out.println(box.getChildren());
		return this.box;
	}

}
