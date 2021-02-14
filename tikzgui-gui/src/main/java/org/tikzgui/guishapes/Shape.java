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
    public void delete();
    public double getBoundingWidth();
    public double getBoundingHeight();
    public double getBoundingX();
    public double getBoundingY();

    public void setBoundingWidth(double width);
    public void setBoundingHeight(double height);
    public void setBoundingX(double x);
    public void setBoundingY(double y);
}
