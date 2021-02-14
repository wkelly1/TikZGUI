package org.tikzgui.core;

public abstract class DimProperty extends Property<Double> {

	protected DimProperty(Double defaultVal, String latexId, String name) {
		super(defaultVal, latexId, name);
	}
	protected DimProperty(Double val, Double defaultVal, String latexId, String name) {
		super(val, defaultVal, latexId, name);
	}
	
	@Override
	public String toString() {
		if (this.get().isEmpty()) {
			return "";
		} else {
			return latexId + " = " + this.get().orElse(-1.0).doubleValue();
		}
	}
	
	
	@Override
	public String getValueString() {
		if(this.get().isEmpty()) {
			return "" + this.getDefault();
		} else {
			return "" + this.get().orElse(-1.0).doubleValue();
		}
	}
}
