package org.tikzgui.texgen;

import java.util.HashMap;

import org.tikzgui.core.GraphicsObject;

class TeXElemClassToPrinterMap { //pure, unadulterated jank
	private HashMap<Class<? extends GraphicsObject>, TeXElementPrinter<? extends GraphicsObject>> internalMap = new HashMap<>();
	public <T extends GraphicsObject> void put(Class<T> graphicsObjectClass, TeXElementPrinter<T> printer) {
		internalMap.put(graphicsObjectClass, printer);
	}
	
	public <T extends GraphicsObject> TeXElementPrinter<T> get(T graphicsObjectClass) {
		//is safe my friend
		return (TeXElementPrinter<T>)internalMap.get(graphicsObjectClass.getClass());
	}
}