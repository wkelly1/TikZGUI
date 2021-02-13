package org.tikzgui.texgen;

import java.util.HashMap;
import org.tikzgui.core.GraphicsObject;
import org.tikzgui.core.Rectangle;

public class Printer {
	
	static HashMap<Class<?>, Printer> printerMappings = new HashMap<>();
	static {
		printerMappings.compute(Rectangle.class, RectanglePrinter);
	}

    public String print(GraphicsObject gObject) {
    	Printer printer = printerMappings.get(gObject);
    	return printer.print(gObject);
    }

}