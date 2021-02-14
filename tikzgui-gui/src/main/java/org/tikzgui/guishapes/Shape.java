package org.tikzgui.guishapes;

import org.tikzgui.core.GraphicsObject;

public interface Shape<T extends GraphicsObject> {

    public boolean isSelected();
    public void select();
    public void unselect();
    public T getGuiElement();
    public void setGuiElement(T obj);
    public void drawBoundingSelectBox();
    public void removeBoundingBox();
    public void removeHover();
    public void setHover();
    public Runnable getUpdate();
}
