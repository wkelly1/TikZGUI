package org.tikzgui.gui;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;



public class ToolbarToggle extends Button {

    private Runnable actionHandler;

    public ToolbarToggle(String text, boolean icon){

        System.out.println(text);
        if (icon){
            this.setGraphic(new ImageView(text));
            this.setStyle("-fx-graphic: url('" + text + "');");
            this.setPrefWidth(50);
        } else {
            this.setText(text);
        }
        this.setPrefHeight(50);

        this.getStyleClass().add("btn");

        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {

                    setStyle("-fx-background-color: #000000");

            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {

                    setStyle("-fx-background-color: transparent");

            }
        });

        this.setOnMousePressed((MouseEvent e) -> this.actionHandler.run());
    }

    public void addActionHandler(Runnable handler){
        this.actionHandler = handler;

    }

}
