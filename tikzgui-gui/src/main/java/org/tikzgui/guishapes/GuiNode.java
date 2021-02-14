package org.tikzgui.guishapes;

import org.tikzgui.core.Node;
import javafx.scene.layout.Pane;

import javafx.scene.text.*;

public class GuiNode extends Text implements Shape<org.tikzgui.core.Node> {
	private boolean isSelected = false;
	private NodeBoundingBox boundingBox;
	private Pane parent;
	private Node guiElement;
	
	
	public GuiNode(Pane parent) {
		this.parent = parent;
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
		boundingBox = new NodeBoundingBox(this.getBoundsInParent());
		parent.getChildren().add(boundingBox.getBox());
		removeHover();
	}

	@Override
	public void removeBoundingBox() {
		parent.getChildren().remove(boundingBox.getBox());
		boundingBox = null;
	}

	@Override
	public void removeHover() {
		styleProperty().set("");
		
	}

	@Override
	public void setHover() {
		styleProperty().set("--fx-border-color: #18A0FB; --fx-border-width: 2");
		
	}

	
	Runnable update = () -> {
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

}
