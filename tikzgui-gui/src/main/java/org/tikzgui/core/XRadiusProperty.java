package org.tikzgui.core;

public class XRadiusProperty extends DimProperty {
	private static String latexId = "x radius";
	private static String name = "X-Radius";
	private static double defaultVal = 2.0;

	public XRadiusProperty() {
		super(defaultVal, latexId, name);

	}

	public XRadiusProperty(Double val) {
		super(val, defaultVal, latexId, name);
	}

}
