package org.tikzgui.core;

public class XRadiusProperty extends DimProperty {
	private static double defaultVal = 1.0;

	public XRadiusProperty() {
		super(defaultVal);

	}

	protected XRadiusProperty(Double val) {
		super(val, defaultVal);
	}

}
