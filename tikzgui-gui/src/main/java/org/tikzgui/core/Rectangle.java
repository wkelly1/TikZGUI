package org.tikzgui.core;

public class Rectangle extends GraphicsObject {
	Stroke stroke;

	@Override
	public PropertySet[] getProperties() {
		return (new PropertySet[] {stroke});
	}
}
