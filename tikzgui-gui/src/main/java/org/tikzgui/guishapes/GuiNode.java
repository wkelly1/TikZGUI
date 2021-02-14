package org.tikzgui.guishapes;

import org.tikzgui.core.Node;
import org.tikzgui.core.Rectangle;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class GuiNode extends Text implements Shape<org.tikzgui.core.Node> {
	private boolean isSelected = false;
	private NodeBoundingBox boundingBox;
	private Pane parent;
	private Node guiElement;
	private javafx.scene.shape.Rectangle tempBoundingBox;
	
	
	public GuiNode(Pane parent) {
		this.parent = parent;
		this.getStyleClass().add("node");
	}

	@Override
	public boolean isSelected() {
		return isSelected;
	}

	@Override
	public void select() {
		isSelected = true;
        drawBoundingSelectBox();
	}

	@Override
	public void unselect() {
		isSelected=false;
		removeBoundingBox();
	}

	@Override
	public Node getGuiElement() {
		return guiElement;
	}

	@Override
	public void setGuiElement(Node guiElement) {
		this.guiElement = guiElement;
	}

	@Override
	public void drawBoundingSelectBox() {
		int pos = this.parent.getChildren().indexOf(this);
		boundingBox = new NodeBoundingBox(this.getBoundsInParent(), this, parent);
		//parent.getChildren().add(pos, boundingBox.getBox());
		removeHover();
	}

	@Override
	public void removeBoundingBox() {
		parent.getChildren().remove(boundingBox.getBox());
		boundingBox = null;
	}

	@Override
	public void removeHover() {
		parent.getChildren().remove(tempBoundingBox);
		this.tempBoundingBox = null;
	}

	@Override
	public void setHover() {
		tempBoundingBox = new javafx.scene.shape.Rectangle(this.getBoundingX(), this.getBoundingY(), this.getBoundingWidth(), this.getBoundingHeight());
		tempBoundingBox.setFill(Color.TRANSPARENT);
		tempBoundingBox.setStrokeWidth(4);
        tempBoundingBox.setStroke(Color.web("#18A0FB"));
        int pos = this.parent.getChildren().indexOf(this);
        this.parent.getChildren().add(pos, tempBoundingBox);
		
		
	}

	
	Runnable update = () -> {
		setText(guiElement.getContents());
        if (boundingBox != null){
            boundingBox.calcOffset();
        }
    };
	@Override
	public Runnable getUpdate() {
		return update;
	}

	@Override
	public void delete() {
		parent.getChildren().remove(this);
		if(boundingBox != null)
			parent.getChildren().remove(boundingBox.getBox());
	}

	@Override
	public double getBoundingWidth() {
		return getBoundsInParent().getWidth();
	}

	@Override
	public double getBoundingHeight() {
		return getBoundsInParent().getHeight();
	}

	@Override
	public double getBoundingX() {
		return getBoundsInParent().getMinX();
	}

	@Override
	public double getBoundingY() {
		return getBoundsInParent().getMinY();
	}

	@Override
	public void setBoundingWidth(double width) {
		// unresizeable
		
	}

	@Override
	public void setBoundingHeight(double height) {
		// unresizeable
	}

	@Override
	public void setBoundingX(double x) {
		setX(x);
		if(guiElement != null) 
			guiElement.getPosition().setX(x);
	}

	@Override
	public void setBoundingY(double y) {
		setY(y);
		if(guiElement != null)
			guiElement.getPosition().setY(y);
	}

	@Override
	public String toString() {
		return "Node ";
	}

}
