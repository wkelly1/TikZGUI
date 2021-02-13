package org.tikzgui.texgen;

import org.tikzgui.core.*;

public class TeXElementPrinterFactory {
	
	
	static TeXElemClassToPrinterMap texElemPrinters= new TeXElemClassToPrinterMap();
	static {
		texElemPrinters.put(PictureContainer.class, (TeXElementPrinter<PictureContainer>)new PictureContainerPrinter());
		texElemPrinters.put(Rectangle.class, (TeXElementPrinter<Rectangle>)new RectanglePrinter());
		texElemPrinters.put(Ellipse.class, (TeXElementPrinter<Ellipse>)new EllipsePrinter());
		texElemPrinters.put(Stroke.class, (TeXElementPrinter<Stroke>)new StrokePrinter());
		texElemPrinters.put(EllipseProps.class, (TeXElementPrinter<EllipseProps>)new EllipsePropsPrinter());
		texElemPrinters.put(LineWidthProperty.class, new PropertyPrinter<LineWidthProperty>()) ;
	}
	
	public static <T extends TeXElement> TeXElementPrinter<T> getPrinter(T object) {
		return texElemPrinters.get(object);
	}
}
