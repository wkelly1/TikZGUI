package org.tikzgui.guishapes;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

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
    private boolean resizing = false;

    public RectResizer(Node parent, Rectangle shape, Node handle, RectBoundingBox bounding, String direction) {
        this.parent = parent;
        this.handle = handle;
        this.shape = shape;
        this.bounding = bounding;

        this.direction = direction;

        handle.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            System.out.println("X: " + shape.getX());
            System.out.println("Y: " + shape.getY());
            System.out.println("Height: " + shape.getHeight());
            switch (direction) {
                case "ul" :
                case "dl" : //moving left
                    this.currentNodeX = shape.getX() + shape.getWidth();
                    break;
                case "ur" :
                case "dr" ://moving right
                    this.currentNodeX = shape.getX();
                    break;
            }
            switch (direction) {
                case "ul":
                case "ur": //moving up
                    this.currentNodeY = shape.getY() + shape.getHeight();
                    System.out.println("here");
                    break;
                case "dl":
                case "dr": //moving right
                    this.currentNodeY = shape.getY();
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

                shape.setWidth(currentWidth);
                shape.setHeight(currentHeight);
                shape.setX(xPos);
                shape.setY(yPos);
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
