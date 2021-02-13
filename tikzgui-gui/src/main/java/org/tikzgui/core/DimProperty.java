package org.tikzgui.core;

public abstract class DimProperty extends Property<Double> {

	protected DimProperty(Double defaultVal) {
		super(defaultVal);
	}
	protected DimProperty(Double val, Double defaultVal) {
		super(val, defaultVal);
	}
	
	@Override
	public String toString() {
		if (this.get().isEmpty()) {
			return "";
		} else {
			return "line width = " + this.get().orElse(-1.0).doubleValue();
		}
	}
}
