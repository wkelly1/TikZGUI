package org.tikzgui.texgen;

import org.tikzgui.core.GraphicsObject;

public class TexGenerator {

    GraphicsObject gObject;


    public TexGenerator (GraphicsObject gObject) {
        this.gObject = gObject;
    }

    public String generate() {

        Printer printer = new Printer();

        String out = "\\begin{tikzpicture} \n";
        
        out = printer.print(gObject) + "\n";

        out += "\\end{tikzpicture}";
        return out;
    }

}