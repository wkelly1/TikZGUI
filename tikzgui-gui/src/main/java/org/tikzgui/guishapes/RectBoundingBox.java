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

    public RectBoundingBox(GuiRectangle innerRect, Node parent) {
        Group group = new Group();
        Rectangle rect = new Rectangle(innerRect.getX(), innerRect.getY(), innerRect.getWidth(), innerRect.getHeight());
        Rectangle tl = new Rectangle(innerRect.getX()-5, innerRect.getY()-5, 10, 10);
        Rectangle tr = new Rectangle(innerRect.getX() + innerRect.getWidth() -5, innerRect.getY()-5, 10, 10);
        Rectangle bl = new Rectangle(innerRect.getX()-5, innerRect.getY()+innerRect.getHeight()-5, 10, 10);
        Rectangle br = new Rectangle(innerRect.getX()+innerRect.getWidth()-5, innerRect.getY()+innerRect.getHeight()-5, 10, 10);

        Rectangle tl_R = new Rectangle(innerRect.getX()-15, innerRect.getY()-15, 10, 10);
        Rectangle tr_R = new Rectangle(innerRect.getX() + innerRect.getWidth() -15, innerRect.getY()-15, 10, 10);
        Rectangle bl_R = new Rectangle(innerRect.getX()-15, innerRect.getY()+innerRect.getHeight()-15, 10, 10);
        Rectangle br_R = new Rectangle(innerRect.getX()+innerRect.getWidth()-15, innerRect.getY()+innerRect.getHeight()-5, 10, 10);

        Pane whOuter = new Pane();
        Label wh = new Label(innerRect.getWidth() + " x " + innerRect.getHeight());
        whOuter.setPadding(new Insets(1.0));
        whOuter.styleProperty().set("-fx-background-color: #18A0FB; -fx-background-radius: 2;");
        wh.setTextFill(Color.WHITE);
        whOuter.getChildren().add(wh);
        whOuter.setLayoutX(innerRect.getX() + (innerRect.getWidth()/2) - 40);
        whOuter.setLayoutY(innerRect.getY() +  innerRect.getHeight() + 5);


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
        group.getChildren().addAll(rect, tl,tr,bl, br, tl_R,tr_R,bl_R, br_R, whOuter);
        this.box = group;
        new RectResizer(parent, innerRect, br, this);
        new RectResizer(parent, innerRect, tl, this);
        new RectResizer(parent, innerRect, tr, this);
        new RectResizer(parent, innerRect, bl, this);
    }

    public Group getBox(){
        return this.box;
    }
}
