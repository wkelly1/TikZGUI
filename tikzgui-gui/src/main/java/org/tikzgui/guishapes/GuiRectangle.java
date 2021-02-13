package org.tikzgui.guishapes;

import javafx.beans.property.DoublePropertyBase;
import javafx.geometry.BoundingBox;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import org.tikzgui.core.GraphicsObject;

public class GuiRectangle extends Rectangle implements Shape{
    private boolean isSelected = false;
    private org.tikzgui.core.Rectangle guiElement;
    private Pane parent;
    private RectBoundingBox boundingBox;
    private Rectangle tempBoundingBox;

    public GuiRectangle(Pane parent) {
        super();
        this.parent = parent;
    }

    public GuiRectangle(double var1, double var3, Pane parent) {
        super(var1, var3);
        this.parent = parent;
    }

    public GuiRectangle(double var1, double var3, Paint var5, Pane parent) {
        super(var1, var3, var5);
        this.parent = parent;
    }

    public GuiRectangle(double var1, double var3, double var5, double var7, Pane parent) {
        super(var1, var3, var5, var7);
        this.parent = parent;
    }


    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void select() {
        isSelected = true;
        drawBoundingSelectBox();
    }

    @Override
    public void unselect() {
        isSelected = false;
        removeBoundingBox();
    }

    @Override
    public org.tikzgui.core.Rectangle getGuiElement() {
        return guiElement;
    }

    @Override
    public void setGuiElement(GraphicsObject obj) {
        this.guiElement = (org.tikzgui.core.Rectangle) obj;
    }

    @Override
    public void drawBoundingSelectBox() {


        int pos = this.parent.getChildren().indexOf(this);

        RectBoundingBox boundingBox = new RectBoundingBox(this, this.parent);



        this.boundingBox = boundingBox;

        this.parent.getChildren().add(pos, boundingBox.getBox());
        removeHover();
    }


    @Override
    public void removeBoundingBox() {
        this.parent.getChildren().remove(this.boundingBox);
        this.boundingBox = null;
    }

    @Override
    public void removeHover() {
        this.parent.getChildren().remove(this.tempBoundingBox);
        this.tempBoundingBox = null;
    }

    @Override
    public void setHover() {
        Rectangle rect = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        rect.setFill(Color.TRANSPARENT);
        rect.setStrokeWidth(4);
        rect.setStroke(Color.web("#18A0FB"));
        this.tempBoundingBox = rect;
        int pos = this.parent.getChildren().indexOf(this);
        this.parent.getChildren().add(pos, rect);
    }
}

