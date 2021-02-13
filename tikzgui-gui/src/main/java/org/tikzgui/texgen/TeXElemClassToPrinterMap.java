package org.tikzgui.texgen;

import java.util.HashMap;

import org.tikzgui.core.GraphicsObject;
import org.tikzgui.core.TeXElement;

class TeXElemClassToPrinterMap { //pure, unadulterated jank
	private HashMap<Class<? extends TeXElement>, TeXElementPrinter<? extends TeXElement>> internalMap = new HashMap<>();
	public <T extends TeXElement> void put(Class<T> texElemClass, TeXElementPrinter<T> printer) {
		internalMap.put(texElemClass, printer);
	}
	
	public <T extends TeXElement> TeXElementPrinter<T> get(T graphicsObjectClass) {
		//is safe my friend
		return (TeXElementPrinter<T>)internalMap.get(graphicsObjectClass.getClass());
	}
}