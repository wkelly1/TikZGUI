package org.tikzgui.core;

public class YRadiusProperty extends DimProperty {
	private static String latexId = "y radius";
	private static String name = "Y-Radius";
	private static double defaultVal = 2.0;

	public YRadiusProperty() {
		super(defaultVal, latexId, name);

	}

	public YRadiusProperty(Double val) {
		super(val, defaultVal, latexId, name);
	}

}
