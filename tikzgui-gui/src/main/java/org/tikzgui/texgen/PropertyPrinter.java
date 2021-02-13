package org.tikzgui.texgen;

import org.tikzgui.core.Property;

public class PropertyPrinter<T extends Property<?>> extends TeXElementPrinter<T> {

	@Override
	public String print(T property, Printer printer) {
		return property.toString();
	}


}
