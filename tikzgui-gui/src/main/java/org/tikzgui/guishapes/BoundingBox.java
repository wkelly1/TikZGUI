package org.tikzgui.guishapes;

import javafx.scene.Group;

public interface BoundingBox {
    public void calcOffset();
    public Group getBox();
}
