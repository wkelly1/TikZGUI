package org.tikzgui.guishapes;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectBoundingBox {
    private Group box;
    private Rectangle rect;
    private Rectangle tl;
    private Rectangle tr;
    private Rectangle bl;
    private Rectangle br;
    private Pane label;
    private Label labelText;
    private GuiRectangle innerRect;


    public RectBoundingBox(GuiRectangle innerRect, Node parent) {
        Group group = new Group();
        this.innerRect = innerRect;

        rect = new Rectangle(innerRect.getX(), innerRect.getY(), innerRect.getWidth(), innerRect.getHeight());
//        tl = new Rectangle(innerRect.getX()-5, innerRect.getY()-5, 10, 10);
//        tr = new Rectangle(innerRect.getX() + innerRect.getWidth() -5, innerRect.getY()-5, 10, 10);
//        bl = new Rectangle(innerRect.getX()-5, innerRect.getY()+innerRect.getHeight()-5, 10, 10);
//        br = new Rectangle(innerRect.getX()+innerRect.getWidth()-5, innerRect.getY()+innerRect.getHeight()-5, 10, 10);

        tl = new Rectangle(10, 10);
        tr = new Rectangle(10, 10);
        bl = new Rectangle(10, 10);
        br = new Rectangle(10, 10);

        Rectangle tl_R = new Rectangle(innerRect.getX() - 15, innerRect.getY() - 15, 10, 10);
        Rectangle tr_R = new Rectangle(innerRect.getX() + innerRect.getWidth() - 15, innerRect.getY() - 15, 10, 10);
        Rectangle bl_R = new Rectangle(innerRect.getX() - 15, innerRect.getY() + innerRect.getHeight() - 15, 10, 10);
        Rectangle br_R = new Rectangle(innerRect.getX() + innerRect.getWidth() - 15, innerRect.getY() + innerRect.getHeight() - 5, 10, 10);

        Pane whOuter = new Pane();
        Label wh = new Label(innerRect.getWidth() + " x " + innerRect.getHeight());
        whOuter.setPadding(new Insets(1.0));
        whOuter.styleProperty().set("-fx-background-color: #18A0FB; -fx-background-radius: 2;");
        wh.setTextFill(Color.WHITE);
        whOuter.getChildren().add(wh);
        whOuter.setLayoutX(innerRect.getX() + (innerRect.getWidth() / 2) - 40);
        whOuter.setLayoutY(innerRect.getY() + innerRect.getHeight() + 5);
        this.label = whOuter;
        this.labelText = wh;


        calcOffset();

        tl_R.setStroke(Color.TRANSPARENT);
        tl_R.setFill(Color.TRANSPARENT);

        tr_R.setStroke(Color.TRANSPARENT);
        tr_R.setFill(Color.TRANSPARENT);

        bl_R.setStroke(Color.TRANSPARENT);
        bl_R.setFill(Color.TRANSPARENT);

        br_R.setStroke(Color.TRANSPARENT);
        br_R.setFill(Color.TRANSPARENT);

        rect.setFill(Color.TRANSPARENT);
        rect.setStrokeWidth(4);
        rect.setStroke(Color.web("#18A0FB"));


        tl.setFill(Color.BLUE);
        tl.setStroke(Color.web("#18A0FB"));
        tl.setStrokeWidth(1);
        tl.setCursor(Cursor.NW_RESIZE);

        tr.setFill(Color.GREEN);
        tr.setStroke(Color.web("#18A0FB"));
        tr.setStrokeWidth(1);
        tr.setCursor(Cursor.NE_RESIZE);

        bl.setFill(Color.GRAY);
        bl.setStroke(Color.web("#18A0FB"));
        bl.setStrokeWidth(1);
        bl.setCursor(Cursor.SW_RESIZE);

        br.setFill(Color.RED);
        br.setStroke(Color.web("#18A0FB"));
        br.setStrokeWidth(1);
        br.setCursor(Cursor.SE_RESIZE);
        group.getChildren().addAll(rect, tl, tr, bl, br, tl_R, tr_R, bl_R, br_R, whOuter);
        this.box = group;
        new RectResizer(parent, innerRect, br, this, "dr");
        new RectResizer(parent, innerRect, tl, this, "ul");
        new RectResizer(parent, innerRect, tr, this, "ur");
        new RectResizer(parent, innerRect, bl, this, "dl");
    }

    //    rect = new Rectangle(innerRect.getX(), innerRect.getY(), innerRect.getWidth(), innerRect.getHeight());
//    tl = new Rectangle(innerRect.getX()-5, innerRect.getY()-5, 10, 10);
//    tr = new Rectangle(innerRect.getX() + innerRect.getWidth() -5, innerRect.getY()-5, 10, 10);
//    bl = new Rectangle(innerRect.getX()-5, innerRect.getY()+innerRect.getHeight()-5, 10, 10);
//    br = new Rectangle(innerRect.getX()+innerRect.getWidth()-5, innerRect.getY()+innerRect.getHeight()-5, 10, 10);
    public void calcOffset() {


        labelText.setText(innerRect.getWidth() + " x " + innerRect.getHeight());
        label.setLayoutX(innerRect.getX() + (innerRect.getWidth() / 2) - 40);
        label.setLayoutY(innerRect.getY() + innerRect.getHeight() + 5);

        rect.setX(innerRect.getX());
        rect.setY(innerRect.getY());
        rect.setWidth(innerRect.getWidth());
        rect.setHeight(innerRect.getHeight());

        tl.setX(innerRect.getX() - 5);
        tl.setY(innerRect.getY() - 5);

        tr.setX(innerRect.getX() + innerRect.getWidth() - 5);
        tr.setY(innerRect.getY() - 5);

        bl.setX(innerRect.getX() - 5);
        bl.setY(innerRect.getY() + innerRect.getHeight() - 5);

        br.setX(innerRect.getX() + innerRect.getWidth() - 5);
        br.setY(innerRect.getY() + innerRect.getHeight() - 5);
    }

    public void setWidth(double width) {
        tr.setX(rect.getX() + width - 5);
        br.setX(rect.getX() + width - 5);
    }

    public void setHeight(double height) {
        bl.setX(rect.getY() + height - 5);
        br.setX(rect.getY() + height - 5);
    }

    public void setX(double x) {

    }


    public Group getBox() {
        return this.box;
    }
}
