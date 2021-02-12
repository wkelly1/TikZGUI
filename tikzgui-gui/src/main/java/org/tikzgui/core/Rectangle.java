package org.tikzgui.core;

public class Rectangle extends GraphicsObject {
	Stroke stroke;
	Point pointA;
	Point pointB;

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

	public Point getPointA() {
		return pointA;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	
	public Point getPointB() {
		return pointB;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}
}
