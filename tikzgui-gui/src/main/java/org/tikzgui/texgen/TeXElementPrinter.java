package org.tikzgui.texgen;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.tikzgui.core.*;

public abstract class TeXElementPrinter<T extends TeXElement> {

	public abstract String print (T teXElement, Printer printer);
	
	protected String printPropertySetArray(PropertySet[] propSets, Printer printer) {
		Arrays.stream(propSets).parallel() //for each property set in the array
								.map(printer::print) //print each property
								.filter(s -> s!="") //filter out those that didn't return anything (i.e. their property will be inherited or use default)
								.collect(Collectors.joining(", ")); //append them all with a comma between each
		return "";
	}
}
