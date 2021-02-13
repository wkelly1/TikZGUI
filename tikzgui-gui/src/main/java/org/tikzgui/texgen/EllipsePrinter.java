package org.tikzgui.texgen;

import org.tikzgui.core.Ellipse;

public class EllipsePrinter extends TeXElementPrinter <ellipse> {
	@Override
    public String print (Ellipse ellipse, Printer printer) {
        String out = "\\draw ";
        out += strokePrinter.print() + " ";
        out += ellipse.getCenter().toString();
        out += " circle ";
        out += printer.print(ellipse.getLocalProperties(), printer);
        out += ";"

        return out;
    }
}