package org.tikzgui.guishapes;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class RectResizer {
    private Node parent;
    private Node handle;
    private Shape shape;
    private BoundingBox bounding;
    private double currentNodeX;
    private double currentNodeY;
    private double currentNodeXEnd;
    private double currentNodeYEnd;
    private String direction;
    private boolean resizing = false;

    public RectResizer(Node parent, Shape shape, Node handle, BoundingBox bounding, String direction) {
        this.parent = parent;
        this.handle = handle;
        this.shape = shape;
        this.bounding = bounding;

        this.direction = direction;

        handle.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            System.out.println("X: " + shape.getBoundingX());
            System.out.println("Y: " + shape.getBoundingY());
            System.out.println("Height: " + shape.getBoundingHeight());
            switch (direction) {
                case "ul" :
                case "dl" : //moving left
                    this.currentNodeX = shape.getBoundingX() + shape.getBoundingWidth();
                    break;
                case "ur" :
                case "dr" ://moving right
                    this.currentNodeX = shape.getBoundingX();
                    break;
            }
            switch (direction) {
                case "ul":
                case "ur": //moving up
                    this.currentNodeY = shape.getBoundingY() + shape.getBoundingHeight();
                    System.out.println("here");
                    break;
                case "dl":
                case "dr": //moving right
                    this.currentNodeY = shape.getBoundingY();
                    System.out.println("not here");
                    break;
            }
            System.out.println("currentX: " + currentNodeX);
            System.out.println("currentY: " + currentNodeY);
            resizing = true;
        });



        parent.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {

            if (resizing) {
                System.out.println(this.direction);
                double xPos = Math.min(event.getX(), this.currentNodeX);
                double yPos = Math.min(event.getY(), this.currentNodeY);
                double currentWidth = Math.abs(event.getX() - this.currentNodeX);
                double currentHeight = Math.abs(this.currentNodeY - event.getY());

                shape.setBoundingWidth(currentWidth);
                shape.setBoundingHeight(currentHeight);
                shape.setBoundingX(xPos);
                shape.setBoundingY(yPos);
                bounding.calcOffset();
            }
        });

        parent.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent event) -> {
            resizing = false;

//            Rectangle rect = new Rectangle(new Point(event.getX() / 10, event.getY() / 10), new Point((event.getX() + 10) / 10, (event.getY() + 10) / 10), rootContainer);
//            rootContainer.addChild(rect);
//            currentNode = null;
        });
    }
}
