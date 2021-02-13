package org.tikzgui.texgen;

import org.tikzgui.core.Circle;

public class CirclePrinter extends TeXElementPrinter<Circle> {
	@Override
    public String print(Circle circle, Printer printer) {
        String out = "\\draw ";
        StrokePrinter strokePrinter = new StrokePrinter(rectangle.getStroke());
        out += strokePrinter.print() + " ";
        out += circle.getCenter().toString();
        out += " circle ";
        out += "[ x radius =" + circle.getXRadius() + ", y radius =" + circle.getYRadius() + "];";

        return out;
    }
}