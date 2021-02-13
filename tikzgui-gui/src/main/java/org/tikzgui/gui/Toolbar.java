package org.tikzgui.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Toolbar extends HBox {
    final private ToolbarButton[] leftContent;
    final private ToolbarButton[] rightContent;

    private String currentAction;

    public Toolbar(ToolbarButton[] leftContent, ToolbarButton[] rightContent){
        // Add the left content
        this.getChildren().addAll(leftContent);
        this.leftContent = leftContent;

        // Add the right content
        HBox hbox = new HBox();
        HBox.setHgrow(hbox, Priority.ALWAYS);
        hbox.getChildren().addAll(rightContent);
        this.rightContent = rightContent;

        // Styling
        this.setStyle("-fx-background-color: #595555;");
        this.setPrefHeight(50);

        leftContent[0].select();
        this.addClickListeners();
    }

    private void addClickListeners(){
        for (ToolbarButton btn : leftContent){
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    deselectAll();
                    System.out.println(btn.getAction());
                    btn.select();
                    setAction(btn.getAction());
                }
            });
        }

        for (ToolbarButton btn : rightContent){
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    deselectAll();
                    System.out.println(btn.getAction());
                    btn.select();
                    setAction(btn.getAction());
                }
            });
        }
    }

    private void deselectAll(){
        for (ToolbarButton btn : leftContent){
            btn.deSelect();
        }

        for (ToolbarButton btn : rightContent){
            btn.deSelect();
        }
    }

    public ToolbarButton getSelected(){
        for (ToolbarButton btn : leftContent){
            if (btn.isSelected()){
                return btn;
            }
        }

        for (ToolbarButton btn : rightContent){
            if (btn.isSelected()){
                return btn;
            }
        }
        return null;
    }

    public String getAction(){
        return this.currentAction;
    }

    public void setAction(String action){
        deselectAll();
        this.currentAction = action;

        for (ToolbarButton btn : leftContent){
            if (btn.getAction().equals(action)){
                btn.select();
            }
        }

        for (ToolbarButton btn : rightContent){
            if (btn.getAction().equals(action)){
                btn.select();
            }
        }
    }
}
