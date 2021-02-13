package org.tikzgui.texgen;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.tikzgui.core.*;

public class PictureContainerPrinter extends TeXElementPrinter<PictureContainer> {

	@Override
	public String print(PictureContainer container, Printer printer) {
		return Arrays.stream(container.getChildren()).parallel() //for each child
												.map(printer::print) //convert the child to TeX
												.collect(Collectors.joining("\n")); //append them all with a newline between each
	}
}
