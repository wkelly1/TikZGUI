package org.tikzgui.texgen;

import org.tikzgui.core.Rectangle;

public class RectanglePrinter extends GraphicsObjectPrinter<Rectangle> {
	@Override
    public String print(Rectangle rectangle) {
        String out = "\\draw ";
        StrokePrinter strokePrinter = new StrokePrinter(rectangle.getStroke());
        out += strokePrinter.print() + " ";
        out += rectangle.getPointA().toString();
        out += " rectangle ";
        out += rectangle.getPointB().toString() + ";";

        return out;
    }
}