package org.tikzgui.texgen;

public class TexGenerator {

    GraphicsObject[] gObjects;


    public TexGenerator (GraphicsObject[] gObjects) {
        this.gObjects = gObjects;
    }

    public String generate() {

        Printer printer = new Printer();

        String out = "\\begin{tikzpicture} \n";
        
        for (GraphicsObject gObject : gObjects) {
            out += printer.print(gObject) + "\n";
        }

        out += "\\end{tikzpicture}";
    }

}