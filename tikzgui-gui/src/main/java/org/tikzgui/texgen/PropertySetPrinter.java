package org.tikzgui.texgen;

import org.tikzgui.core.PropertySet;


public class PropertySetPrinter {
    @Override
    public String print(PropertySet propeties, Printer printer) {
        if (properties.length() == 0) return "";
        String out = "[";
        for (Property property : properties) {
            out += printer.print(property, printer) + ",";
        }
        out = out.substring(0, out.length() - 2) + "]";
        return out;
    }
}