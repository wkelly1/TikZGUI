package org.tikzgui.texgen;

import org.tikzgui.core.PropertySet;
import org.tikzgui.core.Rectangle;

public class RectanglePrinter extends TeXElementPrinter<Rectangle> {
	@Override
    public String print(Rectangle rectangle, Printer printer) {
        String out = "\\draw ";
        out += " [" + this.printPropertySetArray(rectangle.getProperties().toArray(new PropertySet[rectangle.getProperties().size()]), printer) + "] ";
        out += rectangle.getPointA().toLaTeXString();
        out += " rectangle ";
        out += rectangle.getPointB().toLaTeXString();
        out += ";";

        return out;
    }
}