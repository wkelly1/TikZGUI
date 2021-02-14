package org.tikzgui.texgen;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.tikzgui.core.*;

public class PropertySetPrinter extends TeXElementPrinter<PropertySet> {

	@Override
	public String print(PropertySet propSet, Printer printer) {
		System.out.println(propSet.getProperties());
        return Arrays.stream(propSet.getProperties()).parallel() //for each property of this PropertySet
        									.map(printer::print) //print each property
        									.filter(s -> s!="") //filter out those that didn't return anything (i.e. their property will be inherited or use default)
        									.collect(Collectors.joining(", ")); //append them all with a comma between each
    }

}
