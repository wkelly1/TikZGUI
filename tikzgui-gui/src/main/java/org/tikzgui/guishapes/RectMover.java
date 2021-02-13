package org.tikzgui.guishapes;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class RectMover {
    private double difX;
    private double difY;

    private boolean moving = false;

    public RectMover(Node parent, Rectangle shape, Node handle, RectBoundingBox bounding) {
        handle.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            moving = true;
            this.difX = event.getX() - shape.getX();
            this.difY = event.getY() - shape.getY();

        });

        parent.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
            if (moving) {
                event.consume();
                shape.setX(event.getX() - difX);
                shape.setY(event.getY() - difY);
                bounding.calcOffset();
            }
        });

        parent.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent event) -> {
            moving = false;
        });
    }
}
