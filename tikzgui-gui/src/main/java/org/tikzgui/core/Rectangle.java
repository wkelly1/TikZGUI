package org.tikzgui.core;

public class Rectangle extends GraphicsObject {
	Stroke stroke;
	Point topLeft;
	Point topRight;

	@Override
	public PropertySet[] getProperties() {
		return (new PropertySet[] {stroke});
	}

	public Stroke getStroke() {
		return stroke;
	}

	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}

	public Point getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(Point topLeft) {
		this.topLeft = topLeft;
	}

	
	public Point getTopRight() {
		return topRight;
	}

	public void setTopRight(Point topRight) {
		this.topRight = topRight;
	}
}
