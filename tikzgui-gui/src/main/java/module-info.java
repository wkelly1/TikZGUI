module org.tikzgui.gui {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.tikzgui.gui to javafx.fxml;
    exports org.tikzgui.gui;
}