package org.tikzgui.gui;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ToolbarButton extends Button {


    final private String action;
    private boolean isSelected;
    private Cursor cursor;
    private Node parent;

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
