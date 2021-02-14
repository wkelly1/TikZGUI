package org.tikzgui.core;

public class Ellipse extends GraphicsObject {
	Stroke stroke;
	EllipseProps ellipseProps;
	Point centre;
	
	public Ellipse(Point centre, EllipseProps ellipseProps, Stroke stroke, Container parent) {
		super(parent);
		this.centre = centre;
		this.stroke = stroke;
		this.ellipseProps = ellipseProps;
	}

	public Ellipse(Point centre, EllipseProps ellipseProps,  Container parent) {
		this(centre, ellipseProps, new Stroke(), parent);
	}
	
	@Override
	protected PropertySet[] getLocalProperties() {
		return (new PropertySet[] {stroke, ellipseProps});
	}
	
	public Stroke getStroke() {
		return stroke;
	}

	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}
	
	
	public EllipseProps getEllipseProps() {
		return ellipseProps;
	}
	
	public void setEllipseProps(EllipseProps ellipseProps) {
		this.ellipseProps = ellipseProps;
	}
	
	public Point getCentre() {
		return centre;
	}
	
	public void setCentre(Point centre) {
		this.centre = centre;
	}
}
