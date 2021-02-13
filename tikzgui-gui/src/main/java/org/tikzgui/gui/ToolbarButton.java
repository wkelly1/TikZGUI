package org.tikzgui.gui;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.function.Consumer;
import java.util.function.Function;


public class ToolbarButton extends Button {


    final private String action;
    private boolean isSelected;
    private Cursor cursor;
    private Node parent;
    private Runnable actionHandler;

    public ToolbarButton(String action, String text, boolean icon, Cursor cursor, Node parent){
        this.action = action;
        this.cursor = cursor;
        this.parent = parent;

        if (icon){
            this.setGraphic(new ImageView(text));
            this.setStyle("-fx-graphic: url('" + text + "');");
            this.setPrefWidth(50);
        } else {
            this.setText(text);
        }
        this.setId(action);
        this.setPrefHeight(50);

        this.getStyleClass().add("btn");

    }

    public void addActionHandler(Runnable handler){
        this.actionHandler = handler;

    }

    public void runAction(){
        if (this.actionHandler != null) {
            this.actionHandler.run();
        }
    }

    public String getAction() {
        return this.action;
    }



    public boolean isSelected(){
        return this.isSelected;
    }

    public void select(){
        parent.setCursor(this.cursor);
        this.setStyle("-fx-background-color: #5684DF");
        this.isSelected = true;
    }

    public void deSelect(){
        parent.setCursor(Cursor.DEFAULT);
        this.setStyle("-fx-background-color: transparent");
        this.isSelected = false;
    }
}
