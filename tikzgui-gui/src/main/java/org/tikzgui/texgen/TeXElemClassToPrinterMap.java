package org.tikzgui.texgen;

import java.util.HashMap;

import org.tikzgui.core.TeXElement;

class TeXElemClassToPrinterMap { //pure, unadulterated jank
	private HashMap<Class<? extends TeXElement>, TeXElementPrinter<? extends TeXElement>> internalMap = new HashMap<>();
	
	public <T extends TeXElement> void put(Class<T> texElemClass, TeXElementPrinter<? super T> printer) {
		internalMap.put(texElemClass, printer);
	}
	
	public <T extends TeXElement> TeXElementPrinter<? super T> get(T texElemClass) {
		//is safe my friend
		return (TeXElementPrinter<T>)internalMap.get(texElemClass.getClass());
	}
}