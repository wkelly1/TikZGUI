package org.tikzgui.guishapes;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EllipseBoundingBox implements BoundingBox {
    private Group box;
    private Rectangle rect;
    private Rectangle tl;
    private Rectangle tr;
    private Rectangle bl;
    private Rectangle br;
    private Pane label;
    private Label labelText;
    private GuiEllipse innerEllipse;

    final private double boxDim = 10;

    public EllipseBoundingBox(GuiEllipse innerEllipse, Node parent) {
        Group group = new Group();
        this.innerEllipse = innerEllipse;

//        double x = innerEllipse.getCenterX() - innerEllipse.getRadiusX();
//        double y = innerEllipse.getCenterY() - innerEllipse.getRadiusY();
//        double width = innerEllipse.getRadiusX() *2;
//        double height = innerEllipse.getRadiusY() *2;
        
        
        rect = new Rectangle(innerEllipse.getBoundingX(), innerEllipse.getBoundingY(), innerEllipse.getBoundingWidth(), innerEllipse.getBoundingHeight());

        tl = new Rectangle(boxDim, boxDim);
        tr = new Rectangle(boxDim, boxDim);
        bl = new Rectangle(boxDim, boxDim);
        br = new Rectangle(boxDim, boxDim);

//        Rectangle tl_R = new Rectangle(innerRect.getX() - 15, innerRect.getY() - 15, 10, 10);
//        Rectangle tr_R = new Rectangle(innerRect.getX() + innerRect.getWidth() - 15, innerRect.getY() - 15, 10, 10);
//        Rectangle bl_R = new Rectangle(innerRect.getX() - 15, innerRect.getY() + innerRect.getHeight() - 15, 10, 10);
//        Rectangle br_R = new Rectangle(innerRect.getX() + innerRect.getWidth() - 15, innerRect.getY() + innerRect.getHeight() - 5, 10, 10);


        Pane whOuter = new Pane();
        Label wh = new Label(innerEllipse.getBoundingWidth() + " x " + innerEllipse.getBoundingHeight());
        whOuter.setPadding(new Insets(1.0));
        whOuter.styleProperty().set("-fx-background-color: #18A0FB; -fx-background-radius: 2;");
        wh.setTextFill(Color.WHITE);
        whOuter.getChildren().add(wh);
        whOuter.setLayoutX(innerEllipse.getBoundingX() + (innerEllipse.getBoundingWidth() / 2) - 40);
        whOuter.setLayoutY(innerEllipse.getBoundingY() + innerEllipse.getBoundingHeight() + 5);
        this.label = whOuter;
        this.labelText = wh;


        calcOffset();
        System.out.println(innerEllipse);
//        tl_R.setStroke(Color.TRANSPARENT);
//        tl_R.setFill(Color.TRANSPARENT);
//
//        tr_R.setStroke(Color.TRANSPARENT);
//        tr_R.setFill(Color.TRANSPARENT);
//
//        bl_R.setStroke(Color.TRANSPARENT);
//        bl_R.setFill(Color.TRANSPARENT);
//
//        br_R.setStroke(Color.TRANSPARENT);
//        br_R.setFill(Color.TRANSPARENT);

        rect.setFill(Color.TRANSPARENT);
        rect.setStrokeWidth(innerEllipse.getStrokeWidth() + 3);
        rect.setStroke(Color.web("#18A0FB"));
        rect.setCursor(Cursor.MOVE);

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
        group.getChildren().addAll(rect, tl, tr, bl, br, whOuter);
        this.box = group;
        new RectResizer(parent, innerEllipse, br, this, "dr");
        new RectResizer(parent, innerEllipse, tl, this, "ul");
        new RectResizer(parent, innerEllipse, tr, this, "ur");
        new RectResizer(parent, innerEllipse, bl, this, "dl");
        new RectMover(parent, innerEllipse, rect, this);

    }

    public void calcOffset() {
        double strokeWidth = innerEllipse.getStrokeWidth();

        rect.setStrokeWidth(innerEllipse.getStrokeWidth() + 3);

        labelText.setText(innerEllipse.getBoundingWidth() + " x " + innerEllipse.getBoundingHeight());
        label.setLayoutX(innerEllipse.getBoundingX() + (innerEllipse.getBoundingWidth() / 2) - 40);
        label.setLayoutY(innerEllipse.getBoundingY() + innerEllipse.getBoundingHeight() + strokeWidth + 5);

        rect.setX(innerEllipse.getBoundingX());
        rect.setY(innerEllipse.getBoundingY());
        rect.setWidth(innerEllipse.getBoundingWidth());
        rect.setHeight(innerEllipse.getBoundingHeight());

        tl.setX(innerEllipse.getBoundingX() - 0.5*strokeWidth - boxDim);
        tl.setY(innerEllipse.getBoundingY() - 0.5*strokeWidth - boxDim);

        tr.setX(innerEllipse.getBoundingX() + innerEllipse.getBoundingWidth() + 0.5*strokeWidth);
        tr.setY(innerEllipse.getBoundingY() - 0.5*strokeWidth - boxDim);

        bl.setX(innerEllipse.getBoundingX() - 0.5*strokeWidth - boxDim);
        bl.setY(innerEllipse.getBoundingY() + innerEllipse.getBoundingHeight() + 0.5*strokeWidth);

        br.setX(innerEllipse.getBoundingX() + innerEllipse.getBoundingWidth() + 0.5*strokeWidth);
        br.setY(innerEllipse.getBoundingY() + innerEllipse.getBoundingHeight() + 0.5*strokeWidth);
    }

    public Group getBox() {
        return this.box;
    }
}
