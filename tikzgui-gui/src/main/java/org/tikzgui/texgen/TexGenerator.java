package org.tikzgui.texgen;

import org.tikzgui.core.GraphicsObject;
import org.tikzgui.core.TeXElement;

public class TexGenerator {

    TeXElement gObject;


    public TexGenerator (TeXElement gObject) {
        this.gObject = gObject;
    }

    public String generate() {

        Printer printer = new Printer();

        String out =""; 
        
        out += "\\documentclass{standalone}\n";
        out += "\\begin{document}\n";
        out += "\\begin{tikzpicture} \n";
        
        out += printer.print(gObject) + "\n";

        out += "\\end{tikzpicture}\n";
        out += "\\end{document}\n";
        return out;
    }

}