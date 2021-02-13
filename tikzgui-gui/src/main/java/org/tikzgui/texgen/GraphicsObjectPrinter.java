package org.tikzgui.texgen;

import org.tikzgui.core.*;

public abstract class GraphicsObjectPrinter<T extends GraphicsObject> {

	public abstract String print (T graphicsObject);
}
