package org.tikzgui.core;

public class LineWidthProperty extends DimProperty {
	static String latexId = "line width";
	static String name = "Line Width";
	
	public static enum Widths {
		ultraThin (0.25),
		veryThin (0.5),
		thin (1.0),
		semiThick (2.0),
		thick (3.0),
		veryThick (4.0),
		ultraThick (5.0);
		private final double width;
		Widths(double d) {
			this.width=d;
		}
		public double get() {
			return width;
		}
	}
	static Double defaultWidth = 1.0;

	public LineWidthProperty() {
		super(defaultWidth, latexId, name);
	}
	public LineWidthProperty(Double width) {
		super(width, defaultWidth, latexId, name);
	}
	public LineWidthProperty(Widths width) {
		this(width.get());
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
