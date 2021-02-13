package org.tikzgui.texgen;

import org.tikzgui.core.Ellipse;

public class EllipsePrinter extends TeXElementPrinter <ellipse> {
	@Override
    public String print (Ellipse ellipse, Printer printer) {
        String out = "\\draw ";
        StrokePrinter strokePrinter = new StrokePrinter(rectangle.getStroke());
        out += strokePrinter.print() + " ";
        out += ellipse.getCenter().toString();
        out += " circle ";
        out += "[ x radius =" + ellipse.getXRadius() + ", y radius =" + ellipse.getYRadius() + "];";

        return out;
    }
}