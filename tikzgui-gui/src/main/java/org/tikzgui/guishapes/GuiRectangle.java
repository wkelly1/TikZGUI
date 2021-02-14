package org.tikzgui.guishapes;


import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import org.tikzgui.core.GraphicsObject;

import java.util.function.Consumer;

public class GuiRectangle extends Rectangle implements Shape<org.tikzgui.core.Rectangle> {
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
    public void setGuiElement(org.tikzgui.core.Rectangle obj) {
        this.guiElement = obj;
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
        this.parent.getChildren().remove(boundingBox.getBox());
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

    @Override
    public void delete(){
        parent.getChildren().remove(this);
        parent.getChildren().remove(boundingBox.getBox());
    }

    @Override
    public double getBoundingWidth(){
        return this.getWidth();
    }

    @Override
    public double getBoundingHeight(){
        return this.getHeight();
    }

    @Override
    public double getBoundingX(){
        return this.getX();
    }

    @Override
    public double getBoundingY(){
        return this.getY();
    }

    @Override
    public void setBoundingWidth(double width){
        this.setWidth(width);
    }

    @Override
    public void setBoundingHeight(double height){
        this.setHeight(height);
    }

    @Override
    public void setBoundingX(double x){
        this.setX(x);
    }

    @Override
    public void setBoundingY(double y){
        this.setY(y);
    }

    Runnable update = () -> {

        this.setStrokeWidth(getGuiElement().getStroke().getLineWidth().get().orElse(guiElement.getStroke().getLineWidth().getDefault()));
        if (boundingBox != null){
            boundingBox.calcOffset();
        }
    };

    @Override
    public Runnable getUpdate() {
        return update;
    }

    @Override
    public String toString() {
        return "Rectangle ";
    }
}

