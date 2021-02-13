package org.tikzgui.texgen;

import org.tikzgui.core.PropertySet;
import org.tikzgui.core.Rectangle;

public class RectanglePrinter extends TeXElementPrinter<Rectangle> {
	@Override
    public String print(Rectangle rectangle, Printer printer) {
        String printedStroke = printer.print(rectangle.getStroke());
        
        String out = "\\draw ";
        out += rectangle.getPointA().toString();
        out += " rectangle ";
        out += rectangle.getPointB().toString();
        out += " [" + this.printPropertySetArray((PropertySet[]) rectangle.getProperties().toArray(), printer) + "] ";
        out += ";";

        return out;
    }
}