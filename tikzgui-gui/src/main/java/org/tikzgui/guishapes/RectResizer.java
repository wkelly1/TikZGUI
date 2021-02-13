package org.tikzgui.guishapes;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.tikzgui.core.Point;

public class RectResizer {
    private Node parent;
    private Node handle;
    private Node shape;
    private RectBoundingBox bounding;
    private double currentNodeX;
    private double currentNodeY;
    private double currentNodeXEnd;
    private double currentNodeYEnd;
    private String direction;
    private boolean running = false;

    public RectResizer(Node parent, Rectangle shape, Node handle, RectBoundingBox bounding, String direction) {
        this.parent = parent;
        this.handle = handle;
        this.shape = shape;
        this.bounding = bounding;

        this.direction = direction;

        handle.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            this.currentNodeX = shape.getX();
            this.currentNodeY = shape.getY();
            this.currentNodeXEnd = shape.getX() + shape.getWidth();
            this.currentNodeYEnd = shape.getY() + shape.getHeight();
            running = true;
        });

        parent.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {

            if (running) {
                if (direction == "ur" || direction == "dr") {
                    if (event.getX() >= currentNodeX) {
                        shape.setWidth((event.getX() - currentNodeX));
                    } else if (event.getX() < currentNodeX) {
                        shape.setWidth(currentNodeX - event.getX());
                        shape.setX(event.getX() - shape.getStrokeWidth());
                    }
                } else {
                    System.out.println(event.getX() >= currentNodeX);
                    if (event.getX() >= currentNodeX) {
                        if (event.getX() > currentNodeXEnd){
                            shape.setWidth(event.getX() - currentNodeXEnd);
                        } else {

                            shape.setWidth(currentNodeXEnd - event.getX());
                            shape.setX(event.getX());
                        }
                    } else if (event.getX() < currentNodeX) {
                        shape.setWidth(shape.getWidth() + (shape.getX() - event.getX()));
                        shape.setX(event.getX());
                    }
                }

                if (direction == "dl" || direction == "dr") {
                    if (event.getY() >= currentNodeY) {
                        shape.setHeight((event.getY() - currentNodeY));
                    } else if (event.getY() < currentNodeY) {
                        shape.setHeight(currentNodeY - event.getY());
                        shape.setY(event.getY() - shape.getStrokeWidth());
                    }
                } else {
                    System.out.println(event.getY() >= currentNodeY);
                    if (event.getY() >= currentNodeY) {
                        if (event.getY() > currentNodeYEnd){
                            shape.setHeight(event.getY() - currentNodeYEnd);
                        } else {

                            shape.setHeight(currentNodeYEnd - event.getY());
                            shape.setY(event.getY());
                        }
                    } else if (event.getY() < currentNodeY) {
                        shape.setHeight(shape.getHeight() + (shape.getY() - event.getY()));
                        shape.setY(event.getY());
                    }
                }


//                if (event.getY() >= currentNodeY) {
//                    shape.setHeight(event.getY() - currentNodeY);
//                } else if (event.getY() < currentNodeY) {
//                    shape.setHeight(currentNodeY - event.getY());
//                    shape.setY(event.getY() - shape.getStrokeWidth());
//                }




//                if (event.getX() >= currentNodeX) {
//                    if (event.getX() <= currentNodeX) {
//                        shape.setX(event.getX());
//                        shape.setWidth((currentNodeX - event.getX()));
//                    } else {
//                        shape.setWidth((event.getX() - currentNodeX));
//                    }
//                } else if (event.getX() < currentNodeX) {
//                    shape.setWidth(currentNodeX - event.getX());
//                    shape.setX(event.getX());
//                }
//
//                if (event.getY() >= currentNodeY) {
//                    if (event.getY() <= currentNodeY) {
//                        shape.setY(event.getY());
//                        shape.setHeight(currentNodeY - event.getY());
//                    } else {
//                      shape.setHeight(event.getY() - currentNodeY);
//                    }
//                } else if (event.getY() < currentNodeY) {
//                    shape.setHeight(currentNodeY - event.getY());
//                    shape.setY(event.getY());
//
//                }
                bounding.calcOffset();

            }
        });

        parent.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent event) -> {
            running = false;

//            Rectangle rect = new Rectangle(new Point(event.getX() / 10, event.getY() / 10), new Point((event.getX() + 10) / 10, (event.getY() + 10) / 10), rootContainer);
//            rootContainer.addChild(rect);
//            currentNode = null;
        });
    }
}
