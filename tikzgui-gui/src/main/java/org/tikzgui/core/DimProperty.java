package org.tikzgui.core;

public abstract class DimProperty extends Property<Double> {

	protected DimProperty(Double defaultVal) {
		super(defaultVal);
	}
	protected DimProperty(Double val, Double defaultVal) {
		super(val, defaultVal);
	}
}
