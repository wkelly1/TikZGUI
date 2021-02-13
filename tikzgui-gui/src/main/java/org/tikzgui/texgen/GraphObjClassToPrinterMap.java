package org.tikzgui.texgen;

import java.util.HashMap;

import org.tikzgui.core.GraphicsObject;

class GraphObjClassToPrinterMap { //pure, unadulterated jank
	private HashMap<Class<? extends GraphicsObject>, GraphicsObjectPrinter<? extends GraphicsObject>> internalMap = new HashMap<>();
	public <T extends GraphicsObject> void put(Class<T> graphicsObjectClass, GraphicsObjectPrinter<T> printer) {
		internalMap.put(graphicsObjectClass, printer);
	}
	
	public <T extends GraphicsObject> GraphicsObjectPrinter<T> get(T graphicsObjectClass) {
		//is safe my friend
		return (GraphicsObjectPrinter<T>)internalMap.get(graphicsObjectClass.getClass());
	}
}