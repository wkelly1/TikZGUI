package org.tikzgui.texgen;

import org.tikzgui.core.*;

public abstract class TeXElementPrinter<T extends TeXElement> {

	public abstract String print (T graphicsObject, Printer printer);
}
