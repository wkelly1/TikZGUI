package org.tikzgui.core;

public class LineWidthProperty extends DimProperty {
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
		super(defaultWidth);
	}
	public LineWidthProperty(Double width) {
		super(width, defaultWidth);
	}
	public LineWidthProperty(Widths width) {
		this(width.get());
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
