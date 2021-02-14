package org.tikzgui.core;

public class Stroke extends PropertySet {
	private LineWidthProperty lineWidth;

	public Stroke(){
		lineWidth = new LineWidthProperty();
	}

	public Stroke(LineWidthProperty lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	@Override
	public Property<?>[] getProperties() {
		return (new Property[] {lineWidth});
	}
	
	public LineWidthProperty getLineWidth() {
		return lineWidth;
	}
	
	public void setLineWidth(LineWidthProperty lineWidth) {
		this.lineWidth = lineWidth;
	}

}
