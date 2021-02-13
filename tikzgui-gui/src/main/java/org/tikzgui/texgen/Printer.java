package org.tikzgui.texgen;

import org.tikzgui.core.TeXElement;

public class Printer {

    
    public <T extends TeXElement> String print(T obj) {
    	TeXElementPrinter<T> printer = TeXElementPrinterFactory.getPrinter(obj);
    	return printer.print(obj,this);
    }

}