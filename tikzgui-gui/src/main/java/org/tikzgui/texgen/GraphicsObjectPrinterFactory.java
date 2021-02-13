package org.tikzgui.texgen;

import org.tikzgui.core.*;
import java.util.HashMap;

public class GraphicsObjectPrinterFactory {
	
	
	static GraphObjClassToPrinterMap graphicsObjectPrinters= new GraphObjClassToPrinterMap();
	static {
		graphicsObjectPrinters.put(Rectangle.class, (GraphicsObjectPrinter<Rectangle>)new RectanglePrinter());
	}
	
	public static <T extends GraphicsObject> GraphicsObjectPrinter<T> getPrinter(T object) {
		return graphicsObjectPrinters.get(object);
	}
}
