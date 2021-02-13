package org.tikzgui.texgen;

import org.tikzgui.core.Ellipse;

public class EllipsePrinter extends TeXElementPrinter <Ellipse> {
	@Override
    public String print (Ellipse ellipse, Printer printer) {
		StrokePrinter strokePrinter = new StrokePrinter();
		String printedStroke = strokePrinter.print(ellipse.getStroke(), printer);
		EllipsePropsPrinter ellipsePropsPrinter = new EllipsePropsPrinter();
		String printedEllipseProps = ellipsePropsPrinter.print(ellipse.getEllipseProps(), printer);
		String allProps;
		if(printedStroke == "") {
			allProps = printedEllipseProps;
		} else {
			allProps = printedStroke + ", " + printedEllipseProps;
		}
		
		
		
        String out = "\\draw ";
        out += ellipse.getCentre().toString();
        out += " ellipse ";
        out += "[" + allProps + "] ";
        out += ";";

        return out;
    }
}