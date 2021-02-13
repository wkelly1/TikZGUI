package org.tikzgui.guishapes;

import javafx.beans.property.DoublePropertyBase;
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
    private Group boundingBox;
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
        Group group = new Group();
        Rectangle rect = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        Rectangle tl = new Rectangle(this.getX()-5, this.getY()-5, 10, 10);
        Rectangle tr = new Rectangle(this.getX() + this.getWidth() -5, this.getY()-5, 10, 10);
        Rectangle bl = new Rectangle(this.getX()-5, this.getY()+this.getHeight()-5, 10, 10);
        Rectangle br = new Rectangle(this.getX()+this.getWidth()-5, this.getY()+this.getHeight()-5, 10, 10);

        Rectangle tl_R = new Rectangle(this.getX()-15, this.getY()-15, 10, 10);
        Rectangle tr_R = new Rectangle(this.getX() + this.getWidth() -15, this.getY()-15, 10, 10);
        Rectangle bl_R = new Rectangle(this.getX()-15, this.getY()+this.getHeight()-15, 10, 10);
        Rectangle br_R = new Rectangle(this.getX()+this.getWidth()-15, this.getY()+this.getHeight()-5, 10, 10);

        Pane whOuter = new Pane();
        Label wh = new Label(this.getWidth() + " x " + this.getHeight());
        whOuter.setPadding(new Insets(1.0));
        whOuter.styleProperty().set("-fx-background-color: #18A0FB; -fx-background-radius: 2;");
        wh.setTextFill(Color.WHITE);
        whOuter.getChildren().add(wh);
        whOuter.setLayoutX(this.getX() + (this.getWidth()/2) - 40);
        whOuter.setLayoutY(this.getY() +  this.getHeight() + 5);


        tl_R.setStroke(Color.TRANSPARENT);
        tl_R.setFill(Color.TRANSPARENT);
//        tl_R.setCursor(ImageCursor.);

        tr_R.setStroke(Color.TRANSPARENT);
        tr_R.setFill(Color.TRANSPARENT);

        bl_R.setStroke(Color.TRANSPARENT);
        bl_R.setFill(Color.TRANSPARENT);

        br_R.setStroke(Color.TRANSPARENT);
        br_R.setFill(Color.TRANSPARENT);

        rect.setFill(Color.TRANSPARENT);
        rect.setStrokeWidth(4);
        rect.setStroke(Color.web("#18A0FB"));

        int pos = this.parent.getChildren().indexOf(this);

        tl.setFill(Color.WHITE);
        tl.setStroke(Color.web("#18A0FB"));
        tl.setStrokeWidth(1);
        tl.setCursor(Cursor.NW_RESIZE);

        tr.setFill(Color.WHITE);
        tr.setStroke(Color.web("#18A0FB"));
        tr.setStrokeWidth(1);
        tr.setCursor(Cursor.NE_RESIZE);

        bl.setFill(Color.WHITE);
        bl.setStroke(Color.web("#18A0FB"));
        bl.setStrokeWidth(1);
        bl.setCursor(Cursor.SW_RESIZE);

        br.setFill(Color.WHITE);
        br.setStroke(Color.web("#18A0FB"));
        br.setStrokeWidth(1);
        br.setCursor(Cursor.SE_RESIZE);


        this.boundingBox = group;
        group.getChildren().addAll(rect, tl,tr,bl, br, tl_R,tr_R,bl_R, br_R, whOuter);
        this.parent.getChildren().add(pos, group);
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

