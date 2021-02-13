package org.tikzgui.texgen;

import org.tikzgui.core.*;
import java.util.HashMap;

public class TeXElementPrinterFactory {
	
	
	static TeXElemClassToPrinterMap graphicsObjectPrinters= new TeXElemClassToPrinterMap();
	static {
		graphicsObjectPrinters.put(Rectangle.class, (TeXElementPrinter<Rectangle>)new RectanglePrinter());
	}
	
	public static <T extends GraphicsObject> TeXElementPrinter<T> getPrinter(T object) {
		return graphicsObjectPrinters.get(object);
	}
}
