package org.tikzgui.core;

public class LineWidthProperty extends DimProperty {
	static Double defaultWidth;

	public LineWidthProperty() {
		super(defaultWidth);
	}
	public LineWidthProperty(Double width) {
		super(width, defaultWidth);
	}
}
