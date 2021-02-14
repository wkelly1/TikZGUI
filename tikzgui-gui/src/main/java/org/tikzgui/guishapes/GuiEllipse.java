package org.tikzgui.guishapes;


import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import org.tikzgui.core.GraphicsObject;

import java.util.function.Consumer;

public class GuiEllipse extends Ellipse implements Shape<org.tikzgui.core.Ellipse> {
    private boolean isSelected = false;
    private org.tikzgui.core.Ellipse guiElement;
    private Pane parent;
    private EllipseBoundingBox boundingBox;
    private Rectangle tempBoundingBox;


    public GuiEllipse(Pane parent) {
        super();
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
    public org.tikzgui.core.Ellipse getGuiElement() {
        return guiElement;
    }

    @Override
    public void setGuiElement(org.tikzgui.core.Ellipse obj) {
        this.guiElement = obj;
    }

    @Override
    public void drawBoundingSelectBox() {

        int pos = this.parent.getChildren().indexOf(this);

        EllipseBoundingBox boundingBox = new EllipseBoundingBox(this, this.parent);

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
        Rectangle rect = new Rectangle(this.getBoundingX(), this.getBoundingY(), this.getBoundingWidth(), this.getBoundingHeight());
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

    Runnable update = () -> {
        System.out.println("dskfljjsdl");
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
    public double getBoundingWidth(){
        return this.getRadiusX() * 2;
    }

    @Override
    public double getBoundingHeight(){
        return this.getRadiusY() * 2;
    }

    @Override
    public double getBoundingX(){
        return this.getCenterX() - (getRadiusX());
    }

    @Override
    public double getBoundingY(){
        return this.getCenterY() - (getRadiusY());
    }

    @Override
    public void setBoundingWidth(double width){
        this.setRadiusX(width/2);
    }

    @Override
    public void setBoundingHeight(double height){
        this.setRadiusY(height/2);
    }

    @Override
    public void setBoundingX(double x){
        this.setCenterX(x + this.getRadiusX());
    }

    @Override
    public void setBoundingY(double y){
        this.setCenterY(y + this.getRadiusY());
    }
}

