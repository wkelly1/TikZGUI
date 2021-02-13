package org.tikzgui.texgen;

import java.util.HashMap;
import org.tikzgui.core.GraphicsObject;
import org.tikzgui.core.Rectangle;

public class Printer {

    
    public <T extends GraphicsObject> String print(T obj) {
    	TeXElementPrinter<T> printer = TeXElementPrinterFactory.getPrinter(obj);
    	return printer.print(obj);
    }

}