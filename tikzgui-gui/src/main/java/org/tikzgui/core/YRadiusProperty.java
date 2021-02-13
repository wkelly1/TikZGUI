package org.tikzgui.core;

public class YRadiusProperty extends DimProperty {
	private static double defaultVal = 1.0;

	public YRadiusProperty() {
		super(defaultVal);

	}

	protected YRadiusProperty(Double val) {
		super(val, defaultVal);
	}

}
