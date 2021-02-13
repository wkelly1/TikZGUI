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

    private boolean running = false;

    public RectResizer(Node parent, Rectangle shape, Node handle, RectBoundingBox bounding) {
        this.parent = parent;
        this.handle = handle;
        this.shape = shape;
        this.bounding = bounding;
        this.currentNodeX = shape.getX();
        this.currentNodeY = shape.getY();

        handle.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            running = true;
        });

        parent.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {

            if (running) {
                System.out.println(event);
                if (event.getX() >= currentNodeX) {
                    if (event.getX() <= currentNodeX) {
                        shape.setX(event.getX());
                        shape.setWidth((currentNodeX - event.getX()));



                    } else {
                        shape.setWidth((event.getX() - currentNodeX));
                    }
                } else if (event.getX() < currentNodeX) {
                    shape.setWidth(currentNodeX - event.getX());
                    shape.setX(event.getX());
                }

                if (event.getY() >= currentNodeY) {
                    if (event.getY() <= currentNodeY) {
                        shape.setY(event.getY());
                        shape.setHeight(currentNodeY - event.getY());
                    } else {
                      shape.setHeight(event.getY() - currentNodeY);
                    }
                } else if (event.getY() < currentNodeY) {
                    shape.setHeight(currentNodeY - event.getY());
                    shape.setY(event.getY());

                }
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
