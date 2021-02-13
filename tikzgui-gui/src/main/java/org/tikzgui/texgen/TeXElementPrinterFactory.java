package org.tikzgui.texgen;

import org.tikzgui.core.*;
import java.util.HashMap;

public class TeXElementPrinterFactory {
	
	
	static TeXElemClassToPrinterMap graphicsObjectPrinters= new TeXElemClassToPrinterMap();
	static {
		graphicsObjectPrinters.put(PictureContainer.class, (TeXElementPrinter<PictureContainer>)new PictureContainerPrinter());
		graphicsObjectPrinters.put(Rectangle.class, (TeXElementPrinter<Rectangle>)new RectanglePrinter());
		
	}
	
	public static <T extends TeXElement> TeXElementPrinter<T> getPrinter(T object) {
		return graphicsObjectPrinters.get(object);
	}
}
