package org.tikzgui.texgen;

import org.tikzgui.core.*;

public class EllipsePrinter extends TeXElementPrinter <Ellipse> {
	@Override
    public String print (Ellipse ellipse, Printer printer) {
		String printedStroke = printer.print(ellipse.getStroke());
		String printedEllipseProps = printer.print(ellipse.getEllipseProps());
		String allProps;
		if(printedStroke == "") {
			allProps = printedEllipseProps;
		} else {
			allProps = printedStroke + ", " + printedEllipseProps;
		}
		
		
		
        String out = "\\draw ";
		out += ellipse.getCentre().toString();
		out += " ellipse ";
		out += "[" + printPropertySetArray(ellipse.getProperties().toArray(new PropertySet[ellipse.getProperties().size()]), printer) + "] ";
		out += ";";

        return out;
    }
}