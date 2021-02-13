package org.tikzgui.texgen;

import org.tikzgui.core.*;
import java.util.HashMap;

public class TeXElementPrinterFactory {
	
	
	static TeXElemClassToPrinterMap texElemPrinters= new TeXElemClassToPrinterMap();
	static {
		texElemPrinters.put(PictureContainer.class, (TeXElementPrinter<PictureContainer>)new PictureContainerPrinter());
		texElemPrinters.put(Rectangle.class, (TeXElementPrinter<Rectangle>)new RectanglePrinter());
		texElemPrinters.put(Circle.class, (TeXElementPrinter<Circle>)new CirclePrinter());
		texElemPrinters.put(PropertySet.class, (TeXElementPrinter<PropertySet>)new PropertySetPrinter());
		texElemPrinters.put(Stroke.class, (TeXElementPrinter<Stroke)new StrokePrinter());
		
	}
	
	public static <T extends TeXElement> TeXElementPrinter<T> getPrinter(T object) {
		return texElemPrinters.get(object);
	}
}
