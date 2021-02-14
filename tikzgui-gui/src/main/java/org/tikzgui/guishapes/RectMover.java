package org.tikzgui.guishapes;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class RectMover {
    private double difX;
    private double difY;

    private boolean moving = false;

    public RectMover(Node parent, Shape shape, Node handle, BoundingBox bounding) {
        handle.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            moving = true;
            this.difX = event.getX() - shape.getBoundingX();
            this.difY = event.getY() - shape.getBoundingY();

        });

        parent.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
            if (moving) {
                event.consume();
                shape.setBoundingX(event.getX() - difX);
                shape.setBoundingY(event.getY() - difY);
                bounding.calcOffset();
            }
        });

        parent.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent event) -> {
            moving = false;
        });
    }
}
