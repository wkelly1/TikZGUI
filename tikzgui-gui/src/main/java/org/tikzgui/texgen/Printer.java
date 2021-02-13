package org.tikzgui.texgen;

import java.util.HashMap;
import org.tikzgui.core.GraphicsObject;
import org.tikzgui.core.Rectangle;
import org.tikzgui.core.TeXElement;

public class Printer {

    
    public <T extends TeXElement> String print(T obj) {
    	TeXElementPrinter<T> printer = TeXElementPrinterFactory.getPrinter(obj);
    	return printer.print(obj,this);
    }

}