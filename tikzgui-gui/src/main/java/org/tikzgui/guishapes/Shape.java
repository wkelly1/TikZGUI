package org.tikzgui.guishapes;

import org.tikzgui.core.GraphicsObject;

public interface Shape {

    public boolean isSelected();
    public void select();
    public void unselect();
    public GraphicsObject getGuiElement();
    public void setGuiElement(GraphicsObject obj);
    public void drawBoundingSelectBox();
    public void removeBoundingBox();
    public void removeHover();
    public void setHover();
}
