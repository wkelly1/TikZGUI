package org.tikzgui.texgen;

import org.tikzgui.core.*;

public class TeXElementPrinterFactory {
	
	
	static TeXElemClassToPrinterMap texElemPrinters= new TeXElemClassToPrinterMap();
	static TeXElementPrinter<PropertySet> propSetPrinter = new PropertySetPrinter();
	static {
		texElemPrinters.put(PictureContainer.class, (TeXElementPrinter<PictureContainer>)new PictureContainerPrinter());
		texElemPrinters.put(Rectangle.class, (TeXElementPrinter<Rectangle>)new RectanglePrinter());
		texElemPrinters.put(Ellipse.class, (TeXElementPrinter<Ellipse>)new EllipsePrinter());
		texElemPrinters.put(LineWidthProperty.class, new PropertyPrinter<LineWidthProperty>()) ;
		texElemPrinters.put(Stroke.class, propSetPrinter);
		texElemPrinters.put(EllipseProps.class, propSetPrinter);
		texElemPrinters.put(XRadiusProperty.class, new PropertyPrinter<XRadiusProperty>());
		texElemPrinters.put(YRadiusProperty.class, new PropertyPrinter<YRadiusProperty>());
	}
	
	public static <T extends TeXElement> TeXElementPrinter<? super T> getPrinter(T object) {
		return texElemPrinters.get(object);
	}
}
