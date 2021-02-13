package org.tikzgui.texgen;

import org.tikzgui.core.Rectangle;

public class RectanglePrinter extends TeXElementPrinter<Rectangle> {
	@Override
    public String print(Rectangle rectangle, Printer printer) {
        
        String out = "\\draw ";
        
        out += strokePrinter.print() + " ";
        out += rectangle.getPointA().toString();
        out += " rectangle ";
        out += rectangle.getPointB().toString();
        out += printer.print(rectangle.getLocalProperties(), printer);
        out += ";"

        return out;
    }
}