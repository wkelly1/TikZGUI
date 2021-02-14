package org.tikzgui.texgen;

import org.tikzgui.core.PropertySet;
import org.tikzgui.core.Rectangle;

public class RectanglePrinter extends TeXElementPrinter<Rectangle> {
	@Override
    public String print(Rectangle rectangle, Printer printer) {
        String out = "\\draw ";
        out += rectangle.getPointA().toString();
        out += " rectangle ";
        out += " [" + this.printPropertySetArray(rectangle.getProperties().toArray(new PropertySet[rectangle.getProperties().size()]), printer) + "] ";
        out += rectangle.getPointB().toString();
        out += ";";

        return out;
    }
}