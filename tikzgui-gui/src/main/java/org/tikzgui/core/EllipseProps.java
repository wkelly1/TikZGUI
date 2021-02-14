package org.tikzgui.core;

public class EllipseProps extends PropertySet {
	private XRadiusProperty xRadius;
	private YRadiusProperty yRadius;
	
	public EllipseProps(XRadiusProperty xRadius, YRadiusProperty yRadius) {
		this.xRadius = xRadius;
		this.yRadius = yRadius;
	}
	
	public XRadiusProperty getXRadius() {
		return xRadius;
	}
	
	public void setLineWidth(XRadiusProperty xRadius) {
		this.xRadius = xRadius;
	}
	
	public YRadiusProperty getYRadius() {
		return yRadius;
	}
	
	public void setLineWidth(YRadiusProperty yRadius) {
		this.yRadius = yRadius;
	}
	
	
	@Override
	public Property<?>[] getProperties() {
		return (new Property[] {xRadius, yRadius});
	}

}
