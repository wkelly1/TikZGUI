package org.tikzgui.texgen;

import org.tikzgui.core.Rectangle;

public class RectanglePrinter extends TeXElementPrinter<Rectangle> {
	@Override
    public String print(Rectangle rectangle, Printer printer) {
        StrokePrinter strokePrinter = new StrokePrinter();
        String printedStroke = strokePrinter.print(rectangle.getStroke(), printer);
        
        String out = "\\draw ";
        out += rectangle.getPointA().toString();
        out += " rectangle ";
        out += rectangle.getPointB().toString();
        out += " [" + printedStroke + "] ";
        out += ";";

        return out;
    }
}