package org.tikzgui.texgen;

public class TexGenerator {

    GraphicsObject[] gObjects;


    public TexGenerator (GraphicsObject[] gObjects) {
        this.gObjects = gObjects;
    }

    public String generate() {
        
        String out = "\\begin{tikzpicture}";
        for (GraphicsObject gObject : gObjects) {
            out += 
        }
    }

}