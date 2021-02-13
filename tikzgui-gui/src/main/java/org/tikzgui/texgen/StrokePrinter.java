package org.tikzgui.texgen;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.tikzgui.core.Stroke;

public class StrokePrinter extends TeXElementPrinter<Stroke> {
    public String print(Stroke stroke, Printer printer) {
        return Arrays.stream(stroke.getProperties()).parallel() //for each property of this Stroke
        									.map(printer::print) //print each property
        									.filter(s -> s!="") //filter out those that didn't return anything (i.e. their property will be inherited or use default)
        									.collect(Collectors.joining(", ")); //append them all with a comma between each
    }
}